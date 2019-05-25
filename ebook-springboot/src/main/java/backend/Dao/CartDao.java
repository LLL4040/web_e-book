package backend.Dao;

import backend.Entity.Cart;

import java.util.LinkedList;
import com.google.common.base.Optional;

public interface CartDao {
    /**
     * Create methods
     */
    Cart addOne(Cart cart);

    /**
     * Delete methods
     */
    boolean deleteOne(String username, String isbn);

    /**
     * Query methods
     */
    Optional<Cart> findOne(String username, String isbn);
    LinkedList<Cart> findAllByUsername(String username);

    /**
     * Update methods
     */
    Cart updateOne(Cart cart);
}
