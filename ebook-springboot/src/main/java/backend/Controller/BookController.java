package backend.Controller;

import backend.Entity.Book;
import backend.Entity.BookMongoDB;
import backend.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
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

    @PostMapping("/isbn/mongo")
    @ResponseBody
    public BookMongoDB getByIsbn(@RequestBody Map<String, String> map) {
        return bs.findBookMongo(map.get("isbn"));
    }

    /*
    @PostMapping("/add")
    @ResponseBody
    public boolean addBook(@RequestParam MultipartFile cover, @RequestParam String isbn,
                           @RequestParam String bookname, @RequestParam String author,
                           @RequestParam Integer amount, @RequestParam Double price,
                           @RequestParam String authorInfo, @RequestParam String contentInfo) throws IOException {

        return bs.addBook(cover, isbn, bookname, author, amount, price, authorInfo, contentInfo);
    }*/

    @PostMapping("/add")
    @ResponseBody
    public boolean addOrUpdate(@RequestParam MultipartFile cover, @RequestParam String isbn,
                               @RequestParam String bookname, @RequestParam String author,
                               @RequestParam Integer amount, @RequestParam Double price,
                               @RequestParam String authorInfo, @RequestParam String contentInfo) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("isbn", isbn);
        map.put("bookname", bookname);
        map.put("author", author);
        map.put("price", price.toString());
        map.put("amount", amount.toString());
        map.put("authorInfo", authorInfo);
        map.put("contentInfo", contentInfo);
        bs.updateBook(map, cover);
        return true;
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteBook(@RequestBody Map<String, String> map) throws IOException {
        bs.updateBook(map, null);
        return true;
    }
}
