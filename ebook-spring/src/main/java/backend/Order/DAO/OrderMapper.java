package backend.Order.DAO;

import backend.Order.Model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回Order对象
 * */
public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
    // 获取结果集中的数据
        Integer id = resultSet.getInt("order_id");
        String username = resultSet.getString("username");
        String ISBN = resultSet.getString("ISBN");
        String bookname = resultSet.getString("bookname");
        String cover = resultSet.getString("cover");
        Double price = resultSet.getDouble("price");
        Integer number = resultSet.getInt("number");
        String time = resultSet.getString("time");

    // 把数据封装成Order对象
        return new Order(id, username, ISBN, bookname, cover, price, number, time);
    }
}
