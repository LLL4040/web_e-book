package backend.Order.Controller;

import backend.Order.Model.Order;
import backend.Order.Service.OrderService;
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
public class OrderController {
    @Autowired
    OrderService os;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseBody
    public LinkedList<Order> show(@RequestBody Map map) {
        String username = (String)map.get("username");
        return os.searchByUsername(username);
    }
}
