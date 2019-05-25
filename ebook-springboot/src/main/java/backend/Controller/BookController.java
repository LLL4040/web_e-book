package backend.Controller;

import backend.Entity.Book;
import backend.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

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
}
