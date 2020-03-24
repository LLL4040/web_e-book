package backend.Security.AuthLogin;

import java.security.PrivilegedAction;

public class MyPropAction implements PrivilegedAction {
    /**
     Constructs an action for looking up a given property.
     @param propertyName the property name (such as "user.home")
     */
    public MyPropAction(String propertyName) { this.propertyName = propertyName; }

    @Override
    public Object run()
    {
        return System.getProperty(propertyName);
    }

    private String propertyName;
}
