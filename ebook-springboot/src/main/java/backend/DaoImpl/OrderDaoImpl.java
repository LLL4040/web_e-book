package backend.DaoImpl;

import backend.Dao.OrderDao;
import backend.Entity.Order;
import backend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public LinkedList<Order> findAll() {
        return new LinkedList<>(orderRepository.findAll());
    }

    @Override
    public LinkedList<Order> findByUser(String username) {
        return new LinkedList<>(orderRepository.findByUser_Username(username));
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findByOrder_id(id);
    }

    @Override
    public LinkedList<Order> findByUserAndStatus(String username, Integer status) {
        return new LinkedList<>(orderRepository.findByUser_UsernameAndStatus(username, status));
    }

    @Override
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    @Override
    public Order deleteOrder(Order order){
        order.setStatus(0);
        return orderRepository.save(order);
    }
}
