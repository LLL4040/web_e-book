import backend.Security.AuthLogin.MyCallbackHandler;
import backend.Security.AuthLogin.MyPropAction;
import com.sun.security.auth.UnixPrincipal;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Set;

/**
 * 测试自定义用户认证模块
 */
public class TestAuthLogin {
    public static void main(final String[] args) {
        try {
            System.setSecurityManager(new SecurityManager());
            String username = "harry";
            char[] password = new char[]{'s', 'e', 'c', 'r', 'e', 't'};
            LoginContext context = new LoginContext("Login1", new MyCallbackHandler(username, password));
            context.login();
            System.out.println("Authentication successful.");
            Subject subject = context.getSubject();
            System.out.println("subject=" + subject);


            Set<Principal> ps = subject.getPrincipals();
            ps.add(new UnixPrincipal("admin"));
            Principal[] principals = subject.getPrincipals().toArray(new Principal[0]);
            for (Principal p : principals) {
                System.out.println(p.getName());
            }

            PrivilegedAction action = new MyPropAction("os.name");
            Object result = Subject.doAsPrivileged(subject, action, AccessController.getContext());
            System.out.println(result);
            context.logout();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
