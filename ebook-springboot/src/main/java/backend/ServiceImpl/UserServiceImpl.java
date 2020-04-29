package backend.ServiceImpl;

import backend.Dao.UserDao;
import backend.Entity.User;
import backend.Service.UserService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User addUser(User user) {
        user.setStatus(1);
        user.setIdentity(0);
        userDao.addUser(user);
        return userDao.findOne(user.getUsername());
    }

    @Override
    public User findOne(String username) {
        return userDao.findOne(username);
    }

    @Override
    public LinkedList<User> findUserByUsername(String username) {
        Optional<User> user = userDao.findByUsername(username);
        User userFind = user.orNull();
        LinkedList<User> res = new LinkedList<>();
        if (userFind != null) {
            User tmp = new User();
            tmp.setUsername(userFind.getUsername());
            res.add(tmp);
        }
        return res;
    }

//    @Override
//    public List<String> findUserByUsernameContains(String name) {
//        List<User> userList = userDao.findAllByUsernameContains(name);
//        List<String> result = new ArrayList<>();
//        for(User u : userList) {
//            result.add(u.getUsername());
//        }
//        return result;
//    }

    @Override
    public LinkedList<User> findAll(String name, Integer id) {
        try {
            if(userDao.findOne(name).getIdentity() == 1) {
                return userDao.findAll(id);
            }
            else {
                return new LinkedList<>();
            }
        } catch (Exception e) {
            return new LinkedList<>();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteUser(String username) {
        return userDao.deleteUser(username);
    }

    @Override
    public Integer updateUser(Map<String, String> data) {
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
        userDao.addUser(user);
        return 1;
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
    public Integer checkPassword(String username, String password) {
        Optional<User> optionalUser = userDao.findByUsername(username);
        User userFind = optionalUser.orNull();
        if(userFind == null) {
            return -1;
        }
        else if(userFind.getStatus() == 0) {
            return 2;
        }
        else if(password.equals(userFind.getPassword())) {
            return userFind.getIdentity();
        }
        return -1;
    }
}
