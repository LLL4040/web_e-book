package backend.Entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

@SolrDocument(collection = "book_core")
public class BookSolr {
    @Id
    @Field("isbn")
    private String isbn;

    @Field("bookname")
    private String bookname;

    @Field("author")
    private String author;

    @Field("amount")
    private Integer amount;

    @Field("price")
    private Double price;

    @Field("authorInfo")
    private String authorInfo;

    @Field("contentInfo")
    private String contentInfo;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorinfo() {
        return authorInfo;
    }

    public void setAuthorinfo(String authorinfo) {
        this.authorInfo = authorinfo;
    }

    public String getContentinfo() {
        return contentInfo;
    }

    public void setContentinfo(String contentinfo) {
        this.contentInfo = contentinfo;
    }
}
