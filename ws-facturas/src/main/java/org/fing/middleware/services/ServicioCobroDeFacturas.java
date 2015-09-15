package org.fing.middleware.services;

import com.sun.corba.se.spi.activation.Server;
import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import com.sun.xml.ws.transport.http.servlet.WSServletContextListener;
import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by RSperoni on 28/08/2015.
 */

@WebService(endpointInterface = "org.fing.middleware.services.IServicioCobroDeFacturas")
public class ServicioCobroDeFacturas implements IServicioCobroDeFacturas {

    @Resource
    WebServiceContext wsctx;

    public String echo(String echo) {
        return "Ingreso: " + echo;
    }

    public WSResult cobrar(long idFactura, short codMoneda, BigDecimal monto, GregorianCalendar fechaHoraCobro) {
        try{
            if(!isUserAuthenticated())
                throw new Exception("Acceso no autorizado.");

            if(codMoneda != 1 && codMoneda != 2)
                throw new Exception("Moneda no Válida.");

            if(!this.existeFactura(idFactura))
                throw new Exception("Factura no Válida.");

            return new WSResult(true, "Pago procesado correctamente", 0); // TODO Ver como hacer para obtener el id de cobro
        }
        catch (Exception ex){
            return new WSResult(false, ex.getMessage(), 0);
        }
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

    private boolean existeFactura(long idFactura){
        return true; // TODO ver como haccer para obtener si el idFactura es correcto o no.
    }
}
