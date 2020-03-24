package backend.Security.AuthLogin;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MyLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("no handler");
        }

        NameCallback nameCall = new NameCallback("username: ");
        PasswordCallback passCall = new PasswordCallback("password: ", false);
        try {
            callbackHandler.handle(new Callback[]{nameCall, passCall});
        } catch (UnsupportedCallbackException e) {
            LoginException e2 = new LoginException("Unsupported callback");
            e2.initCause(e);
            throw e2;
        } catch (Exception e) {
            LoginException e2 = new LoginException("I/O exception in callback");
            e2.initCause(e);
            throw e2;
        }

        return checkLogin(nameCall.getName(), passCall.getPassword());
    }

    private boolean checkLogin(String username, char[] password) throws LoginException {
        try {
            Scanner in = new Scanner(new FileReader("" + options.get("pwfile")));
            while (in.hasNextLine()) {
                String[] inputs = in.nextLine().split("\\|");
                if (inputs[0].equals(username) && Arrays.equals(inputs[1].toCharArray(), password)) {
                    String role = inputs[2];
                    role.trim();
                    Set<Principal> principals = subject.getPrincipals();
                    principals.add(new MyPrincipal("username", username));
                    principals.add(new MyPrincipal("role", role));
                    return true;
                }
            }
            in.close();
            return false;
        } catch (Exception e) {
            LoginException e2 = new LoginException("Can't open password file");
            e2.initCause(e);
            throw e2;
        }
    }

    @Override
    public boolean logout() {
        return true;
    }

    @Override
    public boolean abort() {
        return true;
    }

    @Override
    public boolean commit() {
        return true;
    }
}
