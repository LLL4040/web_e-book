package backend.Controller;

import backend.Entity.Order;
import backend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService os;

    @PostMapping("/all")
    public LinkedList<Order> getByUser(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        return os.findByUserAndStatus(username, 1);
    }

    @PostMapping("/insert")
    public boolean addOne(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        return os.addOne(username);
    }
}
