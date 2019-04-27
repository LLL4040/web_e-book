package backend.Cart.Controller;

import backend.Cart.Model.Cart;
import backend.Cart.Service.CartService;
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
public class CartController {
    @Autowired
    CartService cs;

    @RequestMapping(value = "/carts", method = RequestMethod.POST)
    @ResponseBody
    public LinkedList<Cart> show(@RequestBody Map map) {
        String username = (String)map.get("username");
        return cs.searchAll(username);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody Map map) {
        String username = (String)map.get("username");
        String ISBN = (String)map.get("ISBN");
        return cs.delete(username, ISBN);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public int ins(@RequestBody Map map) {
        String username = (String)map.get("username");
        String ISBN = (String)map.get("ISBN");
        int num = (int)map.get("num");
        return cs.insert(username, ISBN, num);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestBody Map map) {
        String username = (String)map.get("username");
        String ISBN = (String)map.get("ISBN");
        int num = (int)map.get("num");
        return cs.update(username, ISBN, num);
    }
}
