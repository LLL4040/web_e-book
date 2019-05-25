package backend.Service;

import backend.Entity.Book;
import com.google.common.base.Optional;

import java.util.LinkedList;
import java.util.Map;

public interface BookService {
    Optional<Book> findBookByIsbn(String isbn);

    LinkedList<Book> findAll();

    Book addBook(Book book);

    boolean deleteBook(String isbn);

    Book updateBook(Map<String, String> data);
}
