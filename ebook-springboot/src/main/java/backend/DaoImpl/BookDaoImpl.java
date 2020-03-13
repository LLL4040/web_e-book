package backend.DaoImpl;

import backend.Dao.BookDao;
import backend.Entity.Book;
import backend.Repository.BookRepository;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
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
