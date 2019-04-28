package backend.User.Service;

import backend.User.DAO.UserMapper;
import backend.User.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(User user) {
        int flag = 0;
        try {
            User search = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", new UserMapper(), user.getUsername());
            return flag;
        }catch (Exception e) {
            flag = 1;
            jdbcTemplate.update("insert into users values(?, ?, ?, ?, ?)",
                    user.getUsername(), user.getPassword(), user.getEmail(), user.getStatus(), user.getIdentity());
            return flag;
        }
    }

    @Override
    public int check(String username, String password) {
        int flag = 0;
        try {
            User search = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ? and password = ?", new UserMapper(), username, password);
            if (search.getStatus() == 0) {
                return flag;
            }
            flag = 1;
            return flag;
        }catch (Exception e) {
            flag = 2;
            return flag;
        }
    }

    @Override
    public LinkedList<User> searchAll() {
        List<User> Users = jdbcTemplate.query("SELECT * FROM users", new UserMapper());
        return new LinkedList<>(Users);
    }

    @Override
    public int changeStatus(String username, Integer newStatus) {
        return jdbcTemplate.update("update users set status = ? where username = ?", newStatus, username);
    }

    @Override
    public int changePassword(String username, String newPW) {
        return jdbcTemplate.update("update users set password = ? where username = ?", newPW, username);
    }

    @Override
    public int changeEmail(String username, String newEmail) {
        return jdbcTemplate.update("update users set email = ? where username = ?", newEmail, username);
    }

    @Override
    public int changeIdentity(String username, Integer newID) {
        return jdbcTemplate.update("update users set identity = ? where username = ?", newID, username);
    }
}
