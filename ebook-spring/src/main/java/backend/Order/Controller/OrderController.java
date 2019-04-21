package backend.Order.Controller;

import backend.Order.Model.Order;
import backend.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;

@Controller
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService os;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public LinkedList<Order> pass() {
        return os.searchAll();
    }
}
