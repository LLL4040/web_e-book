package backend.Controller;

import backend.Entity.User;
import backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService us;

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable("name") String username) {
        return us.findUserByUsername(username);
    }

    @PostMapping("/register")
    public boolean register(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String email = map.get("email");
        if(us.nameIsValid(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            us.addUser(user);
            return true;
        }
        return false;
    }

    @PostMapping("/login")
    public Integer login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        return us.checkPassword(username, password);
    }
}
