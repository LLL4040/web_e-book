package backend.Book;

public class Book {
    private String bookname;
    private String author;
    private String cover;
    private String ISBN;
    private Integer amount;
    private Double price;

    public Book(){

    }

    public Book(String bookname, String author, String cover, String ISBN, Integer amount, Double price){
        this.bookname = bookname;
        this.author = author;
        this.cover = cover;
        this.ISBN = ISBN;
        this.amount = amount;
        this.price = price;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookname() {
        return bookname;
    }

    public String getAuthor() {
        return author;
    }

    public String getCover() {
        return cover;
    }

    public String getISBN() {
        return ISBN;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getPrice() {
        return price;
    }
}
