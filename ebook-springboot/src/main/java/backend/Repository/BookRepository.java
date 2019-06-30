package backend.Repository;

import backend.Entity.Book;
import com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findAllByAmountIsNot(Integer num);
}
