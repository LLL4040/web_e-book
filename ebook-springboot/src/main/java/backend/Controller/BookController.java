package backend.Controller;

import backend.Entity.Book;
import backend.Entity.BookMongoDB;
import backend.Entity.BookSolr;
import backend.Service.BookService;
import backend.Service.BookSolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bs;

    @Autowired
    BookSolrService bss;

    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable("isbn") String isbn) {
        return bs.findBookByIsbn(isbn).orNull();
    }

    @GetMapping("/search/{keyword}")
    public List<BookSolr> searchByKey(@PathVariable("keyword") String keyword) {
        return bss.queryAll(keyword);
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
        bss.delete(isbn);
        bss.add(map);
        return true;
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteBook(@RequestBody Map<String, String> map) {
        try {
            String isbn = map.get("isbn");
            bss.delete(isbn);
            return bs.deleteByIsbn(isbn);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

}
