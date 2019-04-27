package backend.Cart.DAO;

import backend.Cart.Model.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回Book对象
 * */
public class CartMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
        // 获取结果集中的数据
        String username = resultSet.getString("username");
        String cover = resultSet.getString("cover");
        String bookname = resultSet.getString("bookname");
        String ISBN = resultSet.getString("ISBN");
        Double price = resultSet.getDouble("price");
        int num = resultSet.getInt("num");
        // 把数据封装成Book对象
        return new Cart(username, cover, bookname, ISBN, price, num);
    }
}
