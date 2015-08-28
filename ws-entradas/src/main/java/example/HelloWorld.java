package main.java.example;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import sun.misc.BASE64Decoder;

import java.io.IOException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by RSperoni on 28/08/2015.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {

    //http://localhost:8080/rest/helloworld/echo/d

    @GET
    @Path("echo/{echostr}")
    @Produces(MediaType.TEXT_PLAIN)
    public String echo(@PathParam("echostr") String echo,@HeaderParam("authorization") String authString) {

        if(!isUserAuthenticated(authString)){
            return "{\"error\":\"User not authenticated\"}";
        }

        return echo;
    }


    private boolean isUserAuthenticated(String authString){

        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        byte[] bytes = null;
        try {
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
            decodedAuth = new String(bytes);
            System.out.println(decodedAuth);

            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }


        /**
         * here you include your logic to validate user authentication.
         * it can be using ldap, or token exchange mechanism or your
         * custom authentication mechanism.
         */
        // your validation code goes here....

        return true;
    }


}
