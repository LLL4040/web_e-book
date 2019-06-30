package backend.Service;

import backend.Entity.Order;

import java.util.LinkedList;

public interface OrderService {
    LinkedList<Order> findAll();
    LinkedList<Order> findByUserAndStatus(String username, Integer status);
    boolean addOne(String username);
    boolean deleteOne(Integer id);
}
