package backend.Service;

import backend.Entity.Person;

import java.util.List;

public interface FriendService {
    /**
     * 查找好友列表
     * @param name
     * @return
     */
    public List<Person> findAll(String name);

    /**
     * 添加好友
     * @param name
     * @param friend
     * @return
     */
    public Boolean addFriend(String name, String friend);

    /**
     * 删除好友
     * @param name
     * @param friend
     * @return
     */
    public Boolean deleteFriend(String name, String friend);
}
