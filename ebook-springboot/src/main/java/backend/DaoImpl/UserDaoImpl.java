package backend.DaoImpl;

import backend.Dao.UserDao;
import backend.Entity.User;
import backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;

import java.util.LinkedList;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(String username) {
        userRepository.deleteByUsername(username);
        return true;
    }

    @Override
    public User findOne(String username) {
        return userRepository.getOne(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public LinkedList<User> findAll(Integer id) {
        return new LinkedList<>(userRepository.findAllByIdentityIs(id));
    }
}
