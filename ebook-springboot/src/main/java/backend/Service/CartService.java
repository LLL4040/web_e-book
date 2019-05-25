package backend.Service;

import backend.Entity.Cart;

import java.util.LinkedList;

public interface CartService {
    LinkedList<Cart> getAll(String username);
    Cart addOne(String username, String isbn, Integer num);
}
