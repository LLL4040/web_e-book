package backend.Order.Model;

import java.util.Date;

public class Order {
    private Integer id;
    private String username;
    private String ISBN;
    private String bookname;
    private String cover;
    private Double price;
    private Integer number;
    private String time;

    public Order(Integer id, String username, String ISBN, String bookname, String cover, Double price, Integer number, String time) {
        this.id = id;
        this.username = username;
        this.ISBN = ISBN;
        this.bookname = bookname;
        this.cover = cover;
        this.price = price;
        this.number = number;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getBookname() {
        return bookname;
    }

    public String getCover() {
        return cover;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }
}
