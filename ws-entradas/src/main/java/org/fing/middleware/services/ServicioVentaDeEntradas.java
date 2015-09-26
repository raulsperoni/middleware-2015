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

@WebService(endpointInterface = "org.fing.middleware.services.IServicioVentaDeEntradas")
public class ServicioVentaDeEntradas implements IServicioVentaDeEntradas {

    @Resource
    WebServiceContext wsctx;

    public String echo(String echo) {
        return "Ingreso: " + echo;
    }

    public WSResult cobrar(short cantEntradas, String codMoneda, BigDecimal monto, GregorianCalendar fechaHoraCobro) {

        WSResult result = new WSResult();

        try{
            if(!isUserAuthenticated())
                throw new Exception("Acceso no autorizado.");

            if(!codMoneda.equals("854") && !codMoneda.equals("840"))
                throw new Exception("Moneda no Válida.");

            if(cantEntradas < 1)
                throw new Exception("La cantidad de entradas tiene que ser mayor que 0.");

            ArrayList<String> codigos = new ArrayList<String>();
            long idCobro = venderEntradas(cantEntradas, codigos);

            result = new WSResult(true, "Pago procesado correctamente", idCobro, codigos);
        }
        catch (Exception ex){
            result = new WSResult(false, ex.getMessage(), 0, new ArrayList<String>());
        }
        finally {
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            fmt.setCalendar(fechaHoraCobro);

            boolean resultLog = appendToLog((new java.util.Date()) + "  ---->  Inbound: " +
                            cantEntradas  + ";" +
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

    private long venderEntradas(int cantidad, ArrayList<String> codigos) throws Exception {

        ServletContext servletContext =
                (ServletContext) wsctx.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

        Integer cantEntradasDisponibles;
        if (servletContext.getAttribute("cantEntradasDisponibles") == null) {
            servletContext.setAttribute("cantEntradasDisponibles", 100);
            cantEntradasDisponibles = 100;
        }
        else
            cantEntradasDisponibles = (Integer)servletContext.getAttribute("cantEntradasDisponibles");

        Long idCobro;
        if (servletContext.getAttribute("idCobro") == null) {
            servletContext.setAttribute("idCobro", new Long(0));
            idCobro = new Long(0);
        }
        else
            idCobro = (Long)servletContext.getAttribute("idCobro");

        if(cantEntradasDisponibles < cantidad)
            throw new Exception("Cantidad no disponible. Máximo disponible: " + cantEntradasDisponibles + ".");
        else
        {
            for(Integer i = 1; i <= cantidad; i++)
                codigos.add((new Integer(100 - cantEntradasDisponibles + i)).toString());

            servletContext.setAttribute("cantEntradasDisponibles", cantEntradasDisponibles - cantidad);
            idCobro ++;
            servletContext.setAttribute("idCobro", idCobro);
            return idCobro;
        }
    }

    private boolean appendToLog(String text, ServletContext context){

        String path = context.getRealPath("/");

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path + "/logServicioEntradas.txt", true)))) {
            out.append(text);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }
    }
}

