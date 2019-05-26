package backend.Service;

import backend.Entity.Book;
import com.google.common.base.Optional;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.Map;

public interface BookService {
    Optional<Book> findBookByIsbn(String isbn);

    LinkedList<Book> findAll();

    Book addBook(Book book);

    @Transactional
    boolean deleteBook(String isbn);

    Book updateBook(Map<String, String> data);
}
