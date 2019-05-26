package backend.ServiceImpl;


import backend.Dao.BookDao;
import backend.Dao.CartDao;
import backend.Dao.UserDao;
import backend.Entity.Book;
import backend.Entity.Cart;
import backend.Entity.User;
import backend.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public LinkedList<Cart> getAll(String username) {
        LinkedList<Cart> cartLinkedList = cartDao.findAllByUsername(username);
        for(Cart cart: cartLinkedList) {
            //cart.setUser(null);
        }
        return cartLinkedList;
    }

    @Override
    public Cart addOne(String username, String isbn, Integer num) {
        User user = userDao.findByUsername(username).orNull();
        Book book = bookDao.findByIsbn(isbn).orNull();
        Cart cart = cartDao.findOne(username, isbn).orNull();
        if(cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setBook(book);
            cart.setNum(num);
            return cartDao.addOne(cart);
        }
        else {
            cart.setNum(num);
            return cartDao.updateOne(cart);
        }
    }

    @Override
    public Cart getOne(String username, String isbn) {
        return cartDao.findOne(username, isbn).orNull();
    }

    @Override
    public boolean deleteOne(String username, String isbn) {
        Cart cart = cartDao.findOne(username, isbn).orNull();
        if(cart == null) {
            return false;
        }
        else {
            cartDao.deleteOne(username, isbn);
            return true;
        }
    }
}
