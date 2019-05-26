package backend.Repository;

import backend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface OrderRepository extends JpaRepository<Order, String> {
    LinkedList<Order> findByUser_Username(String username);
    LinkedList<Order> findByUser_UsernameAndStatus(String username, Integer status);
    LinkedList<Order> findByStatus(Integer status);
}
