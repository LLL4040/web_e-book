package backend.Book.Service;

import backend.Book.DAO.BookMapper;
import backend.Book.Model.Book;
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
    public boolean insert(Book book) {
        try {
            jdbcTemplate.update("insert into books values (?, ?, ?, ?, ?, ?)",
                    book.getBookname(), book.getAuthor(), book.getCover(), book.getISBN(), book.getAmount(), book.getPrice());
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteByISBN(String ISBN) {
        try {
            jdbcTemplate.update("delete from BOOKS where ISBN = ?", ISBN);
            return true;
        }catch (Exception e) {
            return false;
        }
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

    @Override
    public boolean modify(Book book) {
        String isbn = book.getISBN();
        deleteByISBN(isbn);
        return insert(book);
    }

}
