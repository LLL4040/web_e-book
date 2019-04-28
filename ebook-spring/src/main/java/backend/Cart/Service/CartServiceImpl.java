package backend.Cart.Service;

import backend.Book.DAO.BookMapper;
import backend.Book.Model.Book;
import backend.Cart.DAO.CartMapper;
import backend.Cart.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public LinkedList<Cart> searchAll(String username) {
        try {
            List<Cart> carts = jdbcTemplate.query("select * from carts natural join books where username = ?", new CartMapper(), username);
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
                    username, ISBN, num);
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
        int flag = 0;
        try {
            Book old = jdbcTemplate.queryForObject("select * from books where ISBN = ?", new BookMapper(), ISBN);
            if (num > old.getAmount()) {
                return flag;
            }
            flag = 1;
            jdbcTemplate.update("update carts set num = ? where username = ? and ISBN = ?",
                    num, username, ISBN);
            return flag;
        }catch (Exception e) {
            return flag;
        }
    }

    @Override
    public int moveToOrder(String username) {
        int flag = 0;
        try {
            List<Cart> search = jdbcTemplate.query("select * from carts natural join books where username = ?", new CartMapper(), username);
            LinkedList<Cart> cartList = new LinkedList<>(search);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateTime = df.format(new Date()); // Formats a Date into a date/time string.
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    // 获取PreparedStatement，并指定返回自增key
                    PreparedStatement ps = con.prepareStatement("insert into orders(username, time) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, username);
                    ps.setString(2, dateTime);
                    return ps;
                }
            }, keyHolder);
            Number order_id = keyHolder.getKey();
            for (Cart cart : cartList) {
                String isbn = cart.getISBN();
                int num = cart.getNum();
                jdbcTemplate.update("insert into orderitems(order_id, ISBN, number) values(?, ?, ?)", order_id, isbn, num);
                Book old = jdbcTemplate.queryForObject("select * from books where ISBN = ?", new BookMapper(), isbn);
                int newAmount = old.getAmount() - num;
                jdbcTemplate.update("update books set amount = ? where ISBN = ?", newAmount, isbn);
            }
            jdbcTemplate.update("delete from carts where username = ?", username);
            flag = 1;
            return flag;
        }catch (Exception e) {
            return flag;
        }
    }
}
