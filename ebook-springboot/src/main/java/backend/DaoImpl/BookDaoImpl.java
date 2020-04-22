package backend.DaoImpl;

import backend.Dao.BookDao;
import backend.Entity.Book;
import backend.Repository.BookRepository;
import backend.Utils.RedisUtils;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        String isbn = book.getIsbn();
        Object b = redisUtils.get("book" + isbn);
        if (b != null) {
            redisUtils.del("book" + isbn);
        }
        return bookRepository.save(book);
    }

    @Override
    public Boolean deleteByIsbn(String isbn) {
        bookRepository.deleteBookByIsbn(isbn);
        Object b = redisUtils.get("book" + isbn);
        if (b != null) {
            redisUtils.del("book" + isbn);
        }
        return true;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        Optional<Book> book = null;
        Object b = redisUtils.get("book" + isbn);
        if (b == null) {
            book = bookRepository.findByIsbn(isbn);
            redisUtils.set("book" + isbn, JSONArray.toJSON(book.orNull()), 7200);
        } else {
            book = Optional.fromNullable(JSONArray.parseObject(b.toString(), Book.class));
        }
        return book;
    }

    @Override
    public LinkedList<Book> findAll() {
        return new LinkedList<>(bookRepository.findAllByAmountIsNot(0));
    }

    @Override
    public LinkedList<Book> findAllByBookname(String name) {
        return new LinkedList<>(bookRepository.findAllByBooknameEquals(name));
    }
}
