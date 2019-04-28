package backend.Order.Service;

import backend.Order.DAO.OrderMapper;
import backend.Order.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public LinkedList<Order> searchByUsername(String username) {
        try {
            List<Order> orders = jdbcTemplate.query("select * from users natural join orders natural join orderitems natural join books where username = ?", new OrderMapper(), username);
            return new LinkedList<>(orders);
        }catch (Exception e) {
            return new LinkedList<>();
        }
    }

    @Override
    public LinkedList<Order> searchAll() {
        List<Order> orders = jdbcTemplate.query("select order_id, username, ISBN, bookname, cover, price, number, time  from users natural join orders natural join orderitems natural join books", new OrderMapper());
        return new LinkedList<>(orders);
    }
}
