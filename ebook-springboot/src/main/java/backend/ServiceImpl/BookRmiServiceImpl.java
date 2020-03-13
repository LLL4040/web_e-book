package backend.ServiceImpl;

import backend.Dao.BookDao;
import backend.Entity.Book;
import backend.Service.BookRmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class BookRmiServiceImpl implements BookRmiService {
    @Autowired
    private BookDao bookDao;

    @Override
    public LinkedList<Map<Object, Object>> findAllByBookname(String name) {
        LinkedList<Book> books = bookDao.findAllByBookname(name);
        LinkedList<Map<Object, Object>> details = new LinkedList<>();
        for(Book book : books) {
            Map<Object, Object> map = new HashMap<>();
            map.put("bookname", book.getBookname());
            map.put("isbn", book.getIsbn());
            map.put("author", book.getAuthor());
            map.put("amount", book.getAmount());
            map.put("price", book.getPrice());
            map.put("authorinfo", book.getAuthorInfo());
            map.put("contentinfo", book.getContentInfo());
            details.add(map);
        }
        return details;
    }
}
