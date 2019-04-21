package backend.Book.DAO;

import backend.Book.Model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回Book对象
 * */
public class BookMapper implements RowMapper<Book>{

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
    // 获取结果集中的数据
        String bookname = resultSet.getString("bookname");
        String author = resultSet.getString("author");
        String cover = resultSet.getString("cover");
        String ISBN = resultSet.getString("ISBN");
        Integer amount = resultSet.getInt("amount");
        Double price = resultSet.getDouble("price");
        String contentInfo = resultSet.getString("contentInfo");
        String authorInfo = resultSet.getString("authorInfo");
    // 把数据封装成Book对象
        return new Book(bookname, author, cover, ISBN, amount, price, contentInfo, authorInfo);
    }
}
