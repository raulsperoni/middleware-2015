package uy.mgcoders.services;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by raul on 12/11/15.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AnularViaPS {

    @WebMethod
    public String anularReservas(@WebParam(name = "idReserva") long idReserva,@WebParam(name = "servicio") String servicio){
        return null;
    };
}