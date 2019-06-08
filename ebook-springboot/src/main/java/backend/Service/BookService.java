package backend.Service;

import backend.Entity.Book;
import backend.Entity.BookMongoDB;
import com.google.common.base.Optional;

import java.util.LinkedList;
import java.util.Map;

public interface BookService {
    Optional<Book> findBookByIsbn(String isbn);

    LinkedList<Book> findAll();

    Book addBook(Book book);

    Book updateBook(Map<String, String> data);

    BookMongoDB findBookMongo(String isbn);

    void addBookMongo(String isbn, String comment);
}
