package backend.Book;

import backend.Book.Book;

public interface BookService {
    /**
     * 新增一本书
     */
    void insert(Book book);

    /**
     * 根据ISBN删除一本书
     */
    void deleteByISBN(String ISBN);

    /**
     * 根据ISBN查找一本书
     */
    Book searchByISBN(String ISBN);

}
