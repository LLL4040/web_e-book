package backend.User.Controller;

import backend.User.Model.User;
import backend.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;

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
}
