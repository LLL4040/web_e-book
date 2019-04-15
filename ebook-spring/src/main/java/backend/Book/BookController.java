package backend.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    @Autowired
    BookService bs;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        Book book = new Book();

        book = bs.searchByISBN("1234567890123");
        System.out.println("执行完成");

        String out = book.getBookname();

        return out;
    }
}
