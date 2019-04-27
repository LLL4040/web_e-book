package backend.Cart.Service;

import backend.Cart.Model.Cart;

import java.util.LinkedList;

public interface CartService {
    /**
     * 显示购物车内容
     */
    LinkedList<Cart> searchAll(String username);

    /**
     * 点击加购增加一本书
     */
    int insert(String username, String ISBN, int num);

    /**
     * 点击删除删除所有该ISBN的书
     */
    int delete(String username, String ISBN);

    /**
     * 更改该ISBN的书的加购数量
     */
    int update(String username, String ISBN, int num);

    /**
     * 下单时将购物车中所有书转存到订单中，并清空购物车
     */
    int moveToOrder(String username);
}
