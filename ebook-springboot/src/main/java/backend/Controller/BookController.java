package backend.Controller;

import backend.Entity.Book;
import backend.Entity.BookMongoDB;
import backend.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bs;

    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable("isbn") String isbn) {
        return bs.findBookByIsbn(isbn).orNull();
    }

    @GetMapping("/all")
    public LinkedList<Book> getAllBooks() {
        return bs.findAll();
    }

    @GetMapping("/isbn/mongo/{isbn}")
    public BookMongoDB getByIsbn(@PathVariable("isbn") String isbn) {
        return bs.findBookMongo(isbn);
    }

    @PostMapping("/add/mongo")
    public void addMongo(@RequestBody Map<String, String> map) {
        String isbn = map.get("isbn");
        String comment = map.get("comment");
        bs.addBookMongo(isbn, comment);
    }
}
