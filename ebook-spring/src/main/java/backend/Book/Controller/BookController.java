package backend.Book.Controller;

import backend.Book.Service.BookService;
import backend.Book.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookService bs;

    @RequestMapping(value = "/re", method = RequestMethod.GET)
    @ResponseBody
    public Book pass(){
        Book book = bs.searchByISBN("1234567890123");
        System.out.println("执行完成");
        return book;
    }
}
