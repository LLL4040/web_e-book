package backend.Dao;

import backend.Entity.Order;

import java.util.LinkedList;

public interface OrderDao {
    /**
     * Query methods
     */
    Order findById(Integer id);
    LinkedList<Order> findAll();
    LinkedList<Order> findByUser(String username);
    LinkedList<Order> findByUserAndStatus(String username, Integer status);

    /**
     * Create methods
     */
    Order addOrder(Order order);

    /**
     * Delete methods
     */
    Order deleteOrder(Order order);
}
