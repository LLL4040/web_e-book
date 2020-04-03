package backend.ServiceImpl;

import backend.Dao.BookDao;
import backend.Dao.BookMongoDBDao;
import backend.Entity.Book;
import backend.Entity.BookMongoDB;
import backend.Service.BookService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookMongoDBDao bookMongoDBDao;

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookDao.findByIsbn(isbn);
    }

    @Override
    public LinkedList<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Boolean addBook(MultipartFile cover, String isbn, String bookname, String author, Integer amount, Double price, String authorInfo, String contentInfo) throws IOException {
        Book book = bookDao.findByIsbn(isbn).orNull();
        if(book != null) {
            return false;
        }
        else {
            book = new Book();
            book.setIsbn(isbn);
            book.setBookname(bookname);
            book.setAuthor(author);
            book.setCover("");
            book.setAmount(amount);
            book.setPrice(price);
            book.setAuthorInfo(authorInfo);
            book.setContentInfo(contentInfo);
            bookDao.addBook(book);
            bookMongoDBDao.save(new BookMongoDB(isbn, cover.getBytes()));
            return true;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean deleteByIsbn(String isbn) {
        return bookDao.deleteByIsbn(isbn);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book updateBook(Map<String, String> data, MultipartFile cover) throws IOException {
        String isbn = data.get("isbn"), bookname = data.get("bookname"),
                author = data.get("author"), amount = data.get("amount"), price = data.get("price"),
                contentInfo = data.get("contentInfo"), authorInfo = data.get("authorInfo");
        Book book = bookDao.findByIsbn(isbn).orNull();
        if(book == null) {
            book = new Book();
            book.setIsbn(isbn);
            book.setBookname(bookname);
            book.setAuthor(author);
            book.setCover("");
            book.setAmount(Integer.valueOf(amount));
            book.setPrice(Double.valueOf(price));
            book.setContentInfo(contentInfo);
            book.setAuthorInfo(authorInfo);
            bookMongoDBDao.save(new BookMongoDB(isbn, cover.getBytes()));
            return bookDao.addBook(book);
        }
        if(bookname != null) {
            book.setBookname(bookname);
        }
        if(author != null) {
            book.setAuthor(author);
        }
        if(cover != null) {
            BookMongoDB bookMongoDB = bookMongoDBDao.findByIsbn(isbn);
            bookMongoDB.setCover(cover.getBytes());
            bookMongoDBDao.save(bookMongoDB);
        }
        if(amount != null) {
            book.setAmount(Integer.valueOf(amount));
        }
        if(price != null) {
            book.setPrice(Double.valueOf(price));
        }
        if(contentInfo != null) {
            book.setContentInfo(contentInfo);
        }
        if(authorInfo != null) {
            book.setAuthorInfo(authorInfo);
        }

        return bookDao.addBook(book);
    }

    @Override
    public BookMongoDB findBookMongo(String isbn) {
        return bookMongoDBDao.findByIsbn(isbn);
    }

}
