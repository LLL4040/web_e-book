package backend.Dao;

import backend.Entity.User;

import com.google.common.base.Optional;

import java.util.LinkedList;

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
    LinkedList<User> findAll();
}
