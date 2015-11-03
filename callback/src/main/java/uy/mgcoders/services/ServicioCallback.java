package uy.mgcoders.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.Resultado;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * Created by pablo on 10/22/15.
 */
@Stateless
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ServicioCallback {

    private static final Logger logger = LoggerFactory.getLogger(ServicioCallback.class);

    @WebMethod
    public void confirmarOrden(@WebParam(name = "resultado") Resultado resultado) {
        logger.info("metodo: confirmarOrden");
        logger.info("Codigo................: " + resultado.getCodigo());
        logger.info("Identificador de orden: " + resultado.getIdCompra());
        logger.info("#######");
    }

}
