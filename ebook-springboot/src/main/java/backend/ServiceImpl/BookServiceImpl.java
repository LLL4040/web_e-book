package backend.ServiceImpl;

import backend.Dao.BookDao;
import backend.Entity.Book;
import backend.Service.BookService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookDao.findByIsbn(isbn);
    }

    @Override
    public LinkedList<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book addBook(Book book) {
        book.setCover(book.getCover() == null ? "../static/nocover.png": book.getCover());
        return bookDao.addBook(book);
    }

    @Override
    public boolean deleteBook(String isbn) {
        return bookDao.deleteBook(isbn);
    }

    @Override
    public Book updateBook(Map<String, String> data) {
        String isbn = data.get("ISBN"), bookname = data.get("bookname"),
                author = data.get("author"), cover = data.get("cover"),
                amount = data.get("amount"), price = data.get("price"),
                contentInfo = data.get("contentInfo"), authorInfo = data.get("authorInfo");
        Book book = bookDao.findByIsbn(isbn).orNull();
        if(bookname != null) {
            book.setBookname(bookname);
        }
        if(author != null) {
            book.setAuthor(author);
        }
        if(cover != null) {
            book.setCover(cover);
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
}
