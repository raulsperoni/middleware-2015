package uy.mgcoders.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.Resultado;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;
import java.util.Date;

/**
 * Created by pablo on 10/22/15.
 */
@Stateless
@WebService(targetNamespace = "http://callback.mgcoders.uy/")
public class ServicioCallback {

    private static final Logger logger = LoggerFactory.getLogger(ServicioCallback.class);

    @WebMethod
    @RequestWrapper(localName = "ingresarOrdenResponse", targetNamespace = "http://services.mgcoders.uy/")
    public void confirmarOrden(@WebParam(name = "resultado") Resultado resultado) {
        logger.info("########## START --> Servicio Callback --> Confirmar Orden ##########");

        logger.info("Codigo................: " + resultado.getCodigo());
        logger.info("Identificador De Orden: " + resultado.getIdCompra());

        logger.info("########## END --> Servicio Callback --> Confirmar Orden ##########");
    }
}
