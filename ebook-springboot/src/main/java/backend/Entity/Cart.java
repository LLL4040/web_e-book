package backend.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "carts", schema = "ebook", catalog = "")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cart_id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cart_id;

    @ManyToOne(targetEntity = User.class, cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @ManyToOne(targetEntity = Book.class, cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false)
    @JoinColumn(name = "isbn", referencedColumnName = "isbn")
    private Book book;

    @Basic
    @Column(name = "num")
    private Integer num;

    public Integer getCart_id(){ return cart_id; }
    public void setCart_id(Integer cart_id){ this.cart_id = cart_id; }

    public User getUser(){ return user; }
    public void setUser(User user){ this.user = user; }

    public Book getBook(){ return book; }
    public void setBook(Book book){ this.book = book; }

    public Integer getNum(){ return num; }
    public void setNum(Integer num){ this.num = num; }

    @Override
    public boolean equals(Object o){
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Cart that = (Cart) o;
        if(!Objects.equals(cart_id, that.cart_id)) {
            return false;
        }
        if(!Objects.equals(user, that.user)) {
            return false;
        }
        if(!Objects.equals(book, that.book)) {
            return false;
        }
        if(num != null ? num.equals(that.num):that.num != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = 0;
        result = 31 * result + (cart_id != null ? cart_id.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        return result;
    }
}
