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
     * Delete methods
     */
    boolean deleteBook(String isbn);

    /**
     * Query methods
     */
    Optional<Book> findByIsbn(String isbn);
    LinkedList<Book> findAll();
}
