package backend.User.Model;

public class User {
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

    public User(String username, String password, String email, Integer status, Integer identity) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.identity = identity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getIdentity() {
        return identity;
    }
}
