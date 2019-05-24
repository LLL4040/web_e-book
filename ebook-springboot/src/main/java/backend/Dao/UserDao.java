package backend.Dao;

import backend.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.base.Optional;

public interface UserDao {
    /**
     * Create methods
     */
    User addUser(User user);

    /**
     * Delete methods
     */
    boolean deleteUser(String username);

    /**
     * Query methods
     */
    User findOne(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable);
}
