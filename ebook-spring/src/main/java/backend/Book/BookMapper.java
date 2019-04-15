package backend.Book;

import backend.Book.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 * */
public class BookMapper implements RowMapper<Book>{

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//        获取结果集中的数据
        String bookname = resultSet.getString("bookname");
        String author = resultSet.getString("author");
        String cover = resultSet.getString("cover");
        String ISBN = resultSet.getString("ISBN");
        Integer amount = resultSet.getInt("amount");
        Double price = resultSet.getDouble("price");
//        把数据封装成User对象
        Book book = new Book(bookname, author, cover, ISBN, amount, price);
        return book;
    }
}
