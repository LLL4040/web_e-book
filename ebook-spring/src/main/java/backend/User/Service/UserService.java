package backend.User.Service;

import backend.User.Model.User;

import java.util.LinkedList;

public interface UserService {
    /**
     * 新增一个用户（注册）
     */
    int insert(User user);

    /**
     * 查找并核对信息（登录）
     */
    int check(String username, String password);

    /* 管理员功能 */
    /**
     * 查找所有的用户
     */
    LinkedList<User> searchAll();

    /**
     * 禁用或解禁某一用户
     * @return
     */
    int changeStatus(String username, Integer newStatus);

    /* 用户功能 */
    /**
     * 修改密码
     */
    int changePassword(String username, String newPW);

    /**
     * 修改邮箱
     */
    int changeEmail(String username, String newEmail);

    /**
     * 修改身份(管理员和用户之间的转换)
     */
    int changeIdentity(String username, Integer newID);
}
