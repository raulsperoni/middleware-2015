package example;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by RSperoni on 28/08/2015.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {

    //http://localhost:8080/ws-entradas/helloworld/echo/ff

    @GET
    @Path("echo/{echostr}")
    @Produces(MediaType.TEXT_PLAIN)
    public String echo(@PathParam("echostr") String echo) {
        return echo;
    }


}
