package backend.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "ebook", catalog = "")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    /**
     * 0->被禁，1->未被禁
     */
    private Integer status;
    /**
     * 0->普通用户，1->管理员
     */
    private Integer identity;

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "identity")
    public Integer getIdentity() {
        return identity;
    }
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        User that = (User) o;

        if(!username.equals(that.username)) {
            return false;
        }
        if(!password.equals(that.password)) {
            return false;
        }
        if(!email.equals(that.email)) {
            return false;
        }
        if(!status.equals(that.status)) {
            return false;
        }
        if(!identity.equals(that.identity)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((identity == null) ? 0 : identity.hashCode());
        return result;
    }

}
