package backend.ServiceImpl;

import backend.Dao.*;
import backend.Entity.Book;
import backend.Entity.Cart;
import backend.Entity.Order;
import backend.Entity.OrderItem;
import backend.Service.OrderKafkaService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

@Service
public class OrderKafkaServiceImpl implements OrderKafkaService {
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
    @KafkaListener(groupId = "ebook", topics = {"order_topic"})
    public void dealOrder(ConsumerRecord record) {
        String username = (String)record.value();
        LinkedList<Cart> cartLinkedList = cartDao.findAllByUsername(username);
        if(cartLinkedList.isEmpty()) {
            return ;
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
    }
}
