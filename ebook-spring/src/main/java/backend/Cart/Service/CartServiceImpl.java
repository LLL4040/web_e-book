package backend.Cart.Service;

import backend.Cart.DAO.CartMapper;
import backend.Cart.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public LinkedList<Cart> searchAll(String username) {
        try {
            List<Cart> carts = jdbcTemplate.query("select username, cover, bookname, ISBN, price, num from carts natural join books where username = ?", new CartMapper(), username);
            return new LinkedList<>(carts);
        }catch (Exception e) {
            return new LinkedList<>();
        }
    }

    @Override
    public int insert(String username, String ISBN, int num) {
        int flag;
        try {
            Cart search = jdbcTemplate.queryForObject("select * from carts natural join books where username = ? and ISBN = ?",
                    new CartMapper(), username, ISBN);
            flag = 1;
            jdbcTemplate.update("update carts set num = ? where username = ? and ISBN = ?",
                        search.getNum()+1, username, ISBN);
            return flag;
        }catch (Exception e){
            flag = 1;
            jdbcTemplate.update("insert into carts values(?, ?, ?)",
                    username, ISBN, 1);
            return flag;
        }
    }

    @Override
    public int delete(String username, String ISBN) {
        int flag = 0;
        try {
            Cart search = jdbcTemplate.queryForObject("select * from carts natural join books where username = ? and ISBN = ?",
                    new CartMapper(), username, ISBN);
            flag = 1;
            jdbcTemplate.update("delete from carts where username = ? and ISBN = ?", username, ISBN);
            return flag;
        }catch (Exception e){
            return flag;
        }
    }

    @Override
    public int update(String username, String ISBN, int num) {
        int flag = 1;
        jdbcTemplate.update("update carts set num = ? where username = ? and ISBN = ?",
                num, username, ISBN);
        return flag;
    }

    @Override
    public int moveToOrder(String username) {
        int flag = 0;

        return flag;
    }
}
