package backend.Dao;

import backend.Entity.BookMongoDB;
import com.google.common.base.Optional;

public interface BookMongoDBDao {
    void save(BookMongoDB bookMongoDB);

    BookMongoDB findByIsbn(String isbn);
}
