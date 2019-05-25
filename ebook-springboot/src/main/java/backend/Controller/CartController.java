package backend.Controller;

import backend.Entity.Cart;
import backend.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cs;

    @GetMapping("/all/{name}")
    public LinkedList<Cart> getAll(@PathVariable("name") String username) {
        return cs.getAll(username);
    }

    @PostMapping("/insert")
    public Cart addOne(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String isbn = map.get("isbn");
        Integer num = Integer.valueOf(map.get("num"));
        return cs.addOne(username, isbn, num);
    }
}
