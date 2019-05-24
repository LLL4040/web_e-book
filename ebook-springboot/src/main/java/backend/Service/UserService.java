package backend.Service;

import backend.Entity.User;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.Map;

public interface UserService {
    User addUser(User user);

    User findUserByUsername(String username);
    Page<User> findUserByPage(int page, int size);

    @Transactional
    boolean deleteUser(String username);

    User updateUser(Map<String, String> data);

    boolean nameIsValid(String username);

    boolean checkPassword(User user);
}
