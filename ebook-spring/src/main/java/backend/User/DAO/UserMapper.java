package backend.User.DAO;

import backend.User.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RowMapper接口，返回User对象
 * */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
    // 获取结果集中的数据
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        Integer status = resultSet.getInt("status");
        Integer identity = resultSet.getInt("identity");
    // 把数据封装成User对象
        return new User(username, password, email, status, identity);
    }
}
