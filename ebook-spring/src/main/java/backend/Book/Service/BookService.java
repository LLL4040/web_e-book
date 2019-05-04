package backend.Book.Service;

import backend.Book.Model.Book;

import java.util.LinkedList;

public interface BookService {
    /**
     * 新增一本书
     */
    boolean insert(Book book);

    /**
     * 根据ISBN删除一本书
     */
    boolean deleteByISBN(String ISBN);

    /**
     * 根据ISBN查找一本书
     */
    Book searchByISBN(String ISBN);

    /**
     * 查找所有的书籍
     */
    LinkedList<Book> searchAll();

    /**
     * 修改一本书的属性
     */
    boolean modify(Book book);

}
