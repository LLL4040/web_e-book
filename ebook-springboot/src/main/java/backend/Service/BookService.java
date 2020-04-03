package backend.Service;

import backend.Entity.Book;
import backend.Entity.BookMongoDB;
import com.google.common.base.Optional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public interface BookService {
    Optional<Book> findBookByIsbn(String isbn);

    LinkedList<Book> findAll();

    Book updateBook(Map<String, String> data, MultipartFile cover) throws IOException;

    BookMongoDB findBookMongo(String isbn);

    Boolean deleteByIsbn(String isbn);

    Boolean addBook(MultipartFile cover, String isbn, String bookname, String author, Integer amount, Double price, String authorInfo, String contentInfo) throws IOException;
}
