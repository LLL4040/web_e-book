package backend.Service;

import backend.Entity.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.LinkedList;

public interface OrderService {
    LinkedList<Order> findAll();
    LinkedList<Order> findByUserAndStatus(String username, Integer status);
    boolean addOne(String username);
    boolean deleteOne(Integer id);
}
