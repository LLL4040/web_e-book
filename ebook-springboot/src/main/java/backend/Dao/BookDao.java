package backend.Dao;

import backend.Entity.Book;
import com.google.common.base.Optional;

import java.util.LinkedList;

public interface BookDao {
    /**
     * Create methods
     */
    Book addBook(Book book);

    /**
     * Modify methods
     */
    Book updateBook(Book book);

    /**
     * Query methods
     */
    Optional<Book> findByIsbn(String isbn);
    LinkedList<Book> findAll();
}
