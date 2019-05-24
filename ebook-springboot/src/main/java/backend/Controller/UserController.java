package backend.Controller;

import backend.Entity.User;
import backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService us;

    @GetMapping("/api/user/{name}")
    public User getUserByName(@PathVariable("name") String username) {
        return us.findUserByUsername(username);
    }

}
