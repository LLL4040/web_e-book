package backend.Controller;

import backend.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getFriendList(String name) {
        return friendService.findAll(name);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public Boolean addFriend(String name, String friend) {
        return friendService.addFriend(name, friend);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteFriend(String name, String friend) {
        return friendService.deleteFriend(name, friend);
    }
}
