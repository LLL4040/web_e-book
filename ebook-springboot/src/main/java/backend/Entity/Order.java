package backend.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "ebook")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    @JsonBackReference
    @JsonManagedReference
    private Integer order_id;

    @ManyToOne(targetEntity = User.class, cascade={CascadeType.MERGE,CascadeType.REFRESH}, fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Basic
    @Column(name = "time")
    private String time;

    @Basic
    @Column(name = "status")
    /**
     * 0表示已删除，1表示未删除
     */
    private Integer status;

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order_id", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<OrderItem> orderItems;

    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public LinkedList<OrderItem> getOrderItems() {
        return new LinkedList<>(orderItems);
    }
    public void setOrderItems(LinkedList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Order that = (Order) o;
        if(!Objects.equals(order_id, that.order_id)) {
            return false;
        }
        if(!Objects.equals(user, that.user)) {
            return false;
        }
        if(!Objects.equals(time, that.time)) {
            return false;
        }
        if(!Objects.equals(orderItems, that.orderItems)) {
            return false;
        }
        if(status != null ? status.equals(that.status):that.status != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = 0;
        result = 31 * result + (order_id != null ? order_id.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orderItems != null ? orderItems.hashCode() : 0);

        return result;
    }
}
