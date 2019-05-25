package backend.Controller;

import backend.Entity.User;
import backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean register(@RequestBody User user) {
        if(us.nameIsValid(user.getUsername())) {
            us.addUser(user);
            return true;
        }
        return false;
    }

    @PostMapping("/login")
    public Integer login(@RequestBody User user) {
        return us.checkPassword(user);
    }
}
