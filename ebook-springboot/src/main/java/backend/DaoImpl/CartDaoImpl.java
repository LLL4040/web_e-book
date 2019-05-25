package backend.DaoImpl;

import backend.Dao.CartDao;
import backend.Entity.Cart;
import backend.Repository.CartRepository;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addOne(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public boolean deleteOne(String username, String isbn) {
        return cartRepository.deleteAllByUser_UsernameAndBook_Isbn(username, isbn);
    }

    @Override
    public Optional<Cart> findOne(String username, String isbn) {
        return cartRepository.findByUser_UsernameAndBook_Isbn(username, isbn);
    }

    @Override
    public LinkedList<Cart> findAllByUsername(String username) {
        return cartRepository.findAllByUser_Username(username);
    }

    @Override
    public Cart updateOne(Cart cart) {
        return cartRepository.save(cart);
    }
}
