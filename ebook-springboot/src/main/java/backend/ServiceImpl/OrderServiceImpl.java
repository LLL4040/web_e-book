package backend.ServiceImpl;

import backend.Dao.*;
import backend.Entity.Book;
import backend.Entity.Cart;
import backend.Entity.Order;
import backend.Entity.OrderItem;
import backend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    UserDao userDao;

    @Override
    public LinkedList<Order> findAll() {
        LinkedList<Order> orderLinkedList = orderDao.findAll();
        for(Order order: orderLinkedList) {
            order.setUser(null);
        }
        return orderLinkedList;
    }

    @Override
    public LinkedList<Order> findByUserAndStatus(String username, Integer status) {
        try {
            if(userDao.findOne(username).getIdentity() == 1) {
                LinkedList<Order> orderLinkedList = orderDao.findAll();
                for(Order order: orderLinkedList) {
                    order.getUser().setEmail(null);
                    order.getUser().setPassword(null);
                }
                return orderLinkedList;
            }
            else {
                LinkedList<Order> orderLinkedList = orderDao.findByUserAndStatus(username, status);
                for(Order order: orderLinkedList) {
                    order.getUser().setEmail(null);
                    order.getUser().setPassword(null);
                }
                return orderLinkedList;
            }
        } catch (Exception e) {
            return new LinkedList<>();
        }

    }

    @Override
    public boolean addOne(String username) {
        LinkedList<Cart> cartLinkedList = cartDao.findAllByUsername(username);
        if(cartLinkedList.isEmpty()) {
            return false;
        }
        Order order = new Order();
        order.setUser(cartLinkedList.getFirst().getUser());
        order.setStatus(1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date());
        order.setTime(dateTime);
        orderDao.addOrder(order);
        for(Cart cart: cartLinkedList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(order.getOrder_id());
            orderItem.setBook(cart.getBook());
            orderItem.setNumber(cart.getNum());
            orderItemDao.addItem(orderItem);
            Book book = cart.getBook();
            book.setAmount(book.getAmount()-cart.getNum());
            bookDao.updateBook(book);
            cartDao.deleteOne(cart.getUser().getUsername(), cart.getBook().getIsbn());
        }
        return true;
    }

    @Override
    public boolean deleteOne(Integer id) {
        try {
            Order order = orderDao.findById(id);
            orderDao.deleteOrder(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
