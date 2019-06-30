package backend.Service;

import backend.Entity.User;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.Map;

public interface UserService {
    User addUser(User user);

    User findUserByUsername(String username);
    LinkedList<User> findAll(String name, Integer id);

    @Transactional
    boolean deleteUser(String username);

    Integer updateUser(Map<String, String> data);

    boolean nameIsValid(String username);

    Integer checkPassword(String username, String password);
}
