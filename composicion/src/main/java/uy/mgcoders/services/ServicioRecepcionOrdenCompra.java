package uy.mgcoders.services;

import org.apache.cxf.annotations.Policy;
import org.jboss.ws.api.annotation.EndpointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.OrdenCompra;
import uy.mgcoders.dto.Resultado;
import uy.mgcoders.ejb.ComposicionESBHandler;
import uy.mgcoders.wsclient.stock.Producto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 10/22/15.
 */

@Stateless
@WebService
@Addressing(enabled = true, required = true)
@Policy(placement = Policy.Placement.SERVICE, uri = "securitypolicy.xml")
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config-ping.xml")
public class ServicioRecepcionOrdenCompra {

    private static final Logger logger = LoggerFactory.getLogger(ServicioRecepcionOrdenCompra.class);

    @EJB
    ComposicionESBHandler composicionESBHandler;

    @WebMethod
    @WebResult(name = "resultado")
    public Resultado ingresarOrden(@WebParam(name = "ordencompra") OrdenCompra ordenCompra) {

        logger.info("########## START --> Servicio Recepcion Orden de compra --> Ingresar Orden ##########");
        logger.info("idOrden...................: " + ordenCompra.getIdOrden());
        logger.info("nroTarjeta................: " + ordenCompra.getNumeroTarjeta());
        logger.info("Productos.................: ");

        for(int i = 0; i < ordenCompra.getProductos().size(); i++) {
            logger.info("     idProducto................: " + ordenCompra.getProductos().get(i).getIdProducto());
            logger.info("     cantidad..................: " + ordenCompra.getProductos().get(i).getCantidad());
            logger.info("     precioUnitario............: " + ordenCompra.getProductos().get(i).getPrecioUnitario());
            logger.info(" ");
        }

        // Resultado para responder a callback.
        Resultado resultado = composicionESBHandler.procesarOrdenCompra(ordenCompra);

        logger.info("########## END --> Servicio Recepcion Orden de compra --> Ingresar Orden ##########");
        return resultado;
    }

}
