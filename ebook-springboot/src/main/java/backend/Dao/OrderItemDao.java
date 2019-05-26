package backend.Dao;

import backend.Entity.OrderItem;

public interface OrderItemDao {
    /**
     * Create methods
     */
    OrderItem addItem(OrderItem orderItem);
}
