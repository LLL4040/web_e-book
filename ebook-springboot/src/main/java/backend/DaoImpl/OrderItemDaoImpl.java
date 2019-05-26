package backend.DaoImpl;

import backend.Dao.OrderItemDao;
import backend.Entity.OrderItem;
import backend.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem addItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
