package org.fing.middleware.services;

import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

        WSResult result = new WSResult();

        try{
            if(!isUserAuthenticated())
                throw new Exception("Acceso no autorizado.");

            if(codMoneda != 1 && codMoneda != 2)
                throw new Exception("Moneda no Válida.");

            long idCobro = cobrarFactura(idFactura);

            result = new WSResult(true, "Pago procesado correctamente", idCobro);
        }
        catch (Exception ex){
            result = new WSResult(false, ex.getMessage(), 0);
        }
        finally {
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            fmt.setCalendar(fechaHoraCobro);

            boolean resultLog = appendToLog((new java.util.Date()) + "  ---->  Inbound: " +
                                            idFactura  + ";" +
                                            codMoneda + ";" +
                                            monto + ";" +
                                            fmt.format(fechaHoraCobro.getTime())  + "\r\n                                     Outbound: " +
                                            result.toString(),

                    (ServletContext) wsctx.getMessageContext().get(MessageContext.SERVLET_CONTEXT));
            return result;
        }
    }

    private boolean isUserAuthenticated() {

        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

        ArrayList list = (ArrayList) http_headers.get("Authorization");
        if (list == null || list.size() == 0) {
            throw new RuntimeException("Fallo en la Autenticacion!");
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
            throw new RuntimeException("Fallo en la Autenticación!");
        }
        // This should be changed to a DB / Ldap authentication check
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("============== Autenticacion OK =============");
            return true;
        } else {
            throw new RuntimeException("Fallo en la Autenticación!");
        }
    }

    private long cobrarFactura(long idFactura) throws Exception {

        ServletContext servletContext =
                (ServletContext) wsctx.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

        Long idCobro;
        if (servletContext.getAttribute("idCobro") == null) {
            servletContext.setAttribute("idCobro", new Long(0));
            idCobro = new Long(0);
        }
        else
            idCobro = (Long)servletContext.getAttribute("idCobro");

        if(idFactura < 1 || idFactura > 100)
            throw new Exception("Factura no válida.");
        else
        {
            idCobro ++;
            servletContext.setAttribute("idCobro", idCobro);
            return idCobro;
        }
    }

    private boolean appendToLog(String text, ServletContext context){

        String path = context.getRealPath("/");

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path + "/logServicioFactura.txt", true)))) {
            out.append(text);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }
    }
}
