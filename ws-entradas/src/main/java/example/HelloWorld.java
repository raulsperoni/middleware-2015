package example;

import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by RSperoni on 28/08/2015.
 */
@WebService(endpointInterface = "example.IHelloWorld")
public class HelloWorld implements IHelloWorld {

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    public String echo(String echo) {

        if (!isUserAuthenticated()) {
            return "{\"error\":\"User not authenticated\"}";
        }

        return echo;
    }


    private boolean isUserAuthenticated() {

        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

        ArrayList list = (ArrayList) http_headers.get("Authorization");
        if (list == null || list.size() == 0) {
            throw new RuntimeException("Authentication failed! This WS needs BASIC Authentication!");
        }

        String userpass = (String) list.get(0);
        userpass = userpass.substring(5);
        byte[] buf = Base64.decodeBase64(userpass.getBytes());
        String credentials = new String(buf);

        String username = null;
        String password = null;
        int p = credentials.indexOf(":");
        if (p > -1) {
            username = credentials.substring(0, p);
            password = credentials.substring(p + 1);
        } else {
            throw new RuntimeException("There was an error while decoding the Authentication!");
        }
        // This should be changed to a DB / Ldap authentication check
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("============== Authentication OK =============");
            return true;
        } else {
            throw new RuntimeException("Authentication failed! Wrong username / password!");
        }

    }

    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }



}
