package backend.Controller;

import backend.Entity.Person;
import backend.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    @Autowired
    private FriendService fs;

    @PostMapping("/findAll")
    public List<Person> findOne(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        return fs.findAll(username);
    }

    @PostMapping("/add")
    public Boolean addFriend(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String friend = map.get("friend");
        return fs.addFriend(username, friend);
    }

    @PostMapping("/delete")
    public Boolean deleteFriend(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String friend = map.get("friend");
        return fs.deleteFriend(username, friend);
    }
}
