package backend.Repository;

import backend.Entity.Cart;
import com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.LinkedList;

public interface CartRepository extends JpaRepository<Cart, String> {
    LinkedList<Cart> findAllByUser_Username(String username);
    Optional<Cart> findByUser_UsernameAndBook_Isbn(String username, String isbn);

    @Transactional
    void deleteByUser_UsernameAndBook_Isbn(String username, String isbn);
}
