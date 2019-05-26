package backend.Service;

import backend.Entity.Cart;

import javax.transaction.Transactional;
import java.util.LinkedList;

public interface CartService {
    LinkedList<Cart> getAll(String username);
    Cart addOne(String username, String isbn, Integer num);
    Cart getOne(String username, String isbn);
    boolean updateOne(String username, String isbn, Integer num);

    @Transactional
    boolean deleteOne(String username, String isbn);
}
