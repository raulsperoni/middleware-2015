package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by raul on 30/08/15.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IHelloWorld {

    @WebMethod
    String getHelloWorldAsString(String name);

    @WebMethod
    String echo(String echo);
}
