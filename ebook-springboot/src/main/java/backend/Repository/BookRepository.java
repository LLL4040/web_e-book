package backend.Repository;

import backend.Entity.Book;
import com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    @Modifying
    boolean deleteByIsbn(String isbn);
}
