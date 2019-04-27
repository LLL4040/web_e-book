package backend.User.Controller;

import backend.User.Model.User;
import backend.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService us;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public LinkedList<User> pass() {
        return us.searchAll();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public int checkLogin(@RequestBody Map map) {
        String username = (String)map.get("name");
        String password = (String)map.get("pass");
        return us.check(username, password);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public int checkRegister(@RequestBody Map map) {
        String username = (String)map.get("name");
        String password = (String)map.get("pass");
        String email = (String)map.get("email");
        return us.insert(new User(username, password, email, 1, 0));
    }
}
