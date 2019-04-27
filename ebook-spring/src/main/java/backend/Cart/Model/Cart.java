package backend.Cart.Model;

public class Cart {
    private String username;
    private String cover;
    private String bookname;
    private String ISBN;
    private Double price;
    private int num;

    public Cart(String username, String cover, String bookname, String ISBN, Double price, int num) {
        this.username = username;
        this.cover = cover;
        this.bookname = bookname;
        this.ISBN = ISBN;
        this.price = price;
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public String getCover() {
        return cover;
    }

    public String getBookname() {
        return bookname;
    }

    public String getISBN() {
        return ISBN;
    }

    public Double getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }
}
