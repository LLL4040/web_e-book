package backend.Repository;

import backend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedList;

public interface OrderRepository extends JpaRepository<Order, String> {
    LinkedList<Order> findByUser_Username(String username);
    LinkedList<Order> findByUser_UsernameAndStatus(String username, Integer status);
    LinkedList<Order> findByStatus(Integer status);
    @Query(value = "select * from orders where order_id=?1", nativeQuery = true)
    Order findByOrder_id(Integer id);
}
