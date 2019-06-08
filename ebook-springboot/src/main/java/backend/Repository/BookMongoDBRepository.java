package backend.Repository;

import backend.Entity.BookMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookMongoDBRepository extends MongoRepository<BookMongoDB, String> {
    @Query("{'isbn':?0}")
    BookMongoDB findByIsbn(String isbn);
}
