package backend.Repository;

import backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAllByIdentityIs(Integer id);
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByUsername(String username);
}
