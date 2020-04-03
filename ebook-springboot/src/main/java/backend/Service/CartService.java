package backend.Service;

import backend.Entity.Cart;
import java.util.LinkedList;

public interface CartService {
    void test();

    LinkedList<Cart> getAll(String username);
    Cart addOne(String username, String isbn, Integer num);
    Cart getOne(String username, String isbn);
    boolean updateOne(String username, String isbn, Integer num);

    boolean deleteOne(String username, String isbn);
}
