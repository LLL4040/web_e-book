package backend.Controller;

import backend.Entity.Cart;
import backend.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
//    CartService cs0;
    WebApplicationContext applicationContext;

    @GetMapping("/test")
    public void test() {
        CartService cs = applicationContext.getBean(CartService.class);
        System.out.println(cs);
        cs.test();
    }

    @PostMapping("/all")
    public LinkedList<Cart> getAll(@RequestBody Map<String, String> map) {
        CartService cs = applicationContext.getBean(CartService.class);
        String username = map.get("username");
        return cs.getAll(username);
    }

    @GetMapping("/one/{name}&&{isbn}")
    public Cart getOne(@PathVariable("name") String username,
                                   @PathVariable("isbn") String isbn) {
        CartService cs = applicationContext.getBean(CartService.class);
        return cs.getOne(username, isbn);
    }

    @PostMapping("/insert")
    public Cart addOne(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String isbn = map.get("isbn");
        Integer num = Integer.valueOf(map.get("num"));
        CartService cs = applicationContext.getBean(CartService.class);
        return cs.addOne(username, isbn, num);
    }

    @PostMapping("/update")
    public boolean updateOne(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String isbn = map.get("isbn");
        Integer num = Integer.valueOf(map.get("num"));
        CartService cs = applicationContext.getBean(CartService.class);
        return cs.updateOne(username, isbn, num);
    }

    @PostMapping("/delete")
    public boolean deleteOne(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String isbn = map.get("isbn");
        CartService cs = applicationContext.getBean(CartService.class);
        return cs.deleteOne(username, isbn);
    }

}
