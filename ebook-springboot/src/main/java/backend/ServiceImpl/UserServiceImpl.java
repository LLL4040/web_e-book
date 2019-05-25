package backend.ServiceImpl;

import backend.Dao.UserDao;
import backend.Entity.User;
import backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.Map;
import com.google.common.base.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User addUser(User user) {
        user.setStatus(1);
        user.setIdentity(0);
        user.setPassword(user.getPassword());
        userDao.addUser(user);
        return userDao.findOne(user.getUsername());
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findOne(username);
    }

    @Override
    public LinkedList<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean deleteUser(String username) {
        return userDao.deleteUser(username);
    }

    @Override
    public User updateUser(Map<String, String> data) {
        String username = data.get("username");
        User user = userDao.findOne(username);
        String email = data.get("email"), password = data.get("password"), status = data.get("status");
        if(email != null) {
            user.setEmail(email);
        }
        if(password != null) {
            user.setPassword(password);
        }
        if(status != null) {
            user.setStatus(Integer.valueOf(status));
        }
        return userDao.addUser(user);
    }

    @Override
    public boolean nameIsValid(String username) {
        Optional<User> optionalUser = userDao.findByUsername(username);
        User user = optionalUser.orNull();
        if(user != null) {
            return false;
        }
        return true;
    }

    @Override
    public Integer checkPassword(User user) {
        Optional<User> optionalUser = userDao.findByUsername(user.getUsername());
        User userFind = optionalUser.orNull();
        if(userFind == null) {
            return 0;
        }
        else if(userFind.getStatus() == 0) {
            return 2;
        }
        else if(user.getPassword().equals(userFind.getPassword())) {
            return 1;
        }
        return 0;
    }
}
