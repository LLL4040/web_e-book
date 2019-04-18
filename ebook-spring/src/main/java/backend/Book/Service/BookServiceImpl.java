package backend.Book.Service;

import backend.Book.DAO.BookMapper;
import backend.Book.Model.Book;
import backend.Book.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Book book) {
        jdbcTemplate.update("insert into books (bookname, author, cover, ISBN, amount, price) values (?, ?, ?, ?, ?, ?)",
                book.getBookname(), book.getAuthor(), book.getCover(), book.getISBN(), book.getAmount(), book.getPrice());
    }

    @Override
    public void deleteByISBN(String ISBN) {
        jdbcTemplate.update("delete from BOOKS where ISBN = ?", ISBN);
    }

    @Override
    public Book searchByISBN(String ISBN) {
        return jdbcTemplate.queryForObject("SELECT * FROM BOOKS WHERE ISBN = ?", new BookMapper(), ISBN);
    }

    @Override
    public LinkedList<Book> searchAll() {
        List<Book> Books = jdbcTemplate.query("SELECT * FROM books", new BookMapper());
        return new LinkedList<>(Books);
    }

}
