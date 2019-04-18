package backend.Book.Controller;

import backend.Book.Service.BookService;
import backend.Book.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;

@Controller
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookService bs;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public LinkedList<Book> pass(){
        LinkedList<Book> books = bs.searchAll();
        System.out.println("执行完成");
        return books;
    }
}
