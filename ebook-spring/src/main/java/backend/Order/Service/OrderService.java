package backend.Order.Service;

import backend.Order.Model.Order;

import java.util.LinkedList;

public interface OrderService {
    /**
     * 查找某个用户的所有订单
     */
    LinkedList<Order> searchByUsername(String username);

    /**
     * 查找所有用户的所有订单
     */
    LinkedList<Order> searchAll();
}
