package org.fing.middleware.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by raul on 30/08/15.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IServicioVentaDeEntradas {

    @WebMethod
    String echo(String echo);

    @WebMethod
    WSResult cobrar(short cantEntradas, String codMoneda, BigDecimal monto, GregorianCalendar fechaHoraCobro);
}
