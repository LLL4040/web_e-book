package backend.ServiceImpl;

import backend.Dao.FriendDao;
import backend.Dao.UserDao;
import backend.Entity.Person;
import backend.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Person> findAll(String name) {
        if(userDao.findOne(name) == null) {
            return new ArrayList<>();
        }
        List<Person> friendList = friendDao.findAll(name);
        List<Person> res = new ArrayList<>();
        for(Person p : friendList) {
            Person tmp = new Person(p.getName());
            res.add(tmp);
        }
        return res;
    }

    @Override
    public Boolean addFriend(String name, String friend) {
        if(userDao.findOne(name) == null || userDao.findOne(friend) == null) {
            return false;
        }
        return friendDao.addFriend(name, friend);
    }

    @Override
    public Boolean deleteFriend(String name, String friend) {
        if(userDao.findOne(name) == null || userDao.findOne(friend) == null) {
            return false;
        }
        return friendDao.deleteFriend(name, friend);
    }
}
