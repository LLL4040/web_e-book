package backend.Security.AuthLogin;

import java.security.Principal;

public class MyPrincipal implements Principal {
    private String descr;
    private String value;

    public MyPrincipal(String descr, String value) {
        this.descr = descr; this.value = value;
    }
    @Override
    public String getName() {
        return descr + "=" + value;
    }

    @Override
    public boolean equals(Object otherObject)
    {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        MyPrincipal other = (MyPrincipal) otherObject;
        return getName().equals(other.getName());
    }
    @Override
    public int hashCode() { return getName().hashCode(); }
}
