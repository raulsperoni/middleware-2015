package uy.mgcoders.security;

import org.apache.ws.security.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pablo on 11/7/15.
 */
public class KeystorePasswordCallback implements CallbackHandler {

    private Map<String, String> passwords = new HashMap<String, String>();
    private static final Logger logger = LoggerFactory.getLogger(KeystorePasswordCallback.class);

    public KeystorePasswordCallback() {
        passwords.put("server", "middleware2015");
        passwords.put("client", "middleware2015");
        logger.debug("constructor - KeystorePasswordCallback");
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];

            String pass = passwords.get(pc.getIdentifier());
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }
    }

    public void setAliasPassword(String alias, String password) {
        passwords.put(alias, password);
    }
}
