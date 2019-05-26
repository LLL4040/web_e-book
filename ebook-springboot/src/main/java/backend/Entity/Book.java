package backend.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table(name = "books", schema = "ebook", catalog = "")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Book {
    @Id
    @JsonBackReference
    @JsonManagedReference
    private String isbn;
    private String bookname;
    private String author;
    private String cover;
    private Integer amount;
    private Double price;
    private String contentinfo;
    private String authorinfo;

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String ISBN) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "bookname")
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "contentinfo")
    public String getContentInfo() {
        return contentinfo;
    }
    public void setContentInfo(String contentInfo) {
        this.contentinfo = contentInfo;
    }

    @Basic
    @Column(name = "authorinfo")
    public String getAuthorInfo() {
        return authorinfo;
    }
    public void setAuthorInfo(String authorInfo) {
        this.authorinfo = authorInfo;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Book that = (Book) o;

        if(!isbn.equals(that.isbn)) {
            return false;
        }
        if(!bookname.equals(that.bookname)) {
            return false;
        }
        if(!author.equals(that.author)) {
            return false;
        }
        if(!cover.equals(that.cover)) {
            return false;
        }
        if(!amount.equals(that.amount)) {
            return false;
        }
        if(!price.equals(that.price)) {
            return false;
        }
        if(!contentinfo.equals(that.contentinfo)) {
            return false;
        }
        if(!authorinfo.equals(that.authorinfo)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((bookname == null) ? 0 : bookname.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((cover == null) ? 0 : cover.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((contentinfo == null) ? 0 : contentinfo.hashCode());
        result = prime * result + ((authorinfo == null) ? 0 : authorinfo.hashCode());
        return result;
    }
}
