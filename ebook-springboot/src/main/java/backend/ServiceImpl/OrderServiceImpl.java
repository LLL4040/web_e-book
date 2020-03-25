package backend.ServiceImpl;

import backend.Dao.*;
import backend.Entity.Book;
import backend.Entity.Cart;
import backend.Entity.Order;
import backend.Entity.OrderItem;
import backend.Service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    KafkaTemplate kafkaTemplate;

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
        kafkaTemplate.send("order_topic", username);
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
