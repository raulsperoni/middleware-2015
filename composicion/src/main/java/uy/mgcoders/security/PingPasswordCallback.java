package uy.mgcoders.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ws.security.WSPasswordCallback;

public class PingPasswordCallback implements CallbackHandler {

    private final static Logger s_logger = LoggerFactory.getLogger(PingPasswordCallback.class);

    private Map<String, String> passwords = new HashMap<String, String>();

    public PingPasswordCallback() {
        passwords.put("server", "middleware2015");
        passwords.put("client", "middleware2015");
    }

    /**
     * It attempts to get the password from the private
     * alias/passwords map.
     */
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
            System.out.println("Requested password for " + pc.getIdentifier());

            String pass = passwords.get(pc.getIdentifier());
            if (pass != null) {
                pc.setPassword(pass);
                return;
            } else {
                pc.setPassword("password");
            }
        }
    }
}