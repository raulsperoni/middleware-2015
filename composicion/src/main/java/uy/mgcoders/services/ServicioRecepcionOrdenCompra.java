package uy.mgcoders.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.OrdenCompra;
import uy.mgcoders.dto.Resultado;
import uy.mgcoders.wsclient.stock.Producto;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 10/22/15.
 */

// TODO esto esta pendiente todo!

@Stateless
@WebService
@Addressing(enabled = true, required = true)
/*@EndpointProperties(value = {
        @EndpointProperty(key = "ws-security.signature.properties", value = "serversecurity.properties"),
        @EndpointProperty(key = "ws-security.encryption.properties", value = "serversecurity.properties"),
        @EndpointProperty(key = "ws-security.signature.username", value = "server"),
        @EndpointProperty(key = "ws-security.encryption.username", value = "client"),
        @EndpointProperty(key = "ws-security.callback-handler", value = "uy.mgcoders.security.KeystorePasswordCallback")
    }
)*/
//@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
public class ServicioRecepcionOrdenCompra {

    @Resource
    WebServiceContext context;

    private static final Logger logger = LoggerFactory.getLogger(ServicioRecepcionOrdenCompra.class);

    @WebMethod
    @WebResult(name = "resultado")
    public Resultado ingresarOrden(@WebParam(name = "ordencompra") OrdenCompra ordenCompra) {

        logger.info("########## START --> Servicio Recepcion Orden de compra --> Ingresar Orden ##########");
        logger.info("idOrden...................: " + ordenCompra.getIdOrden());
        logger.info("nroTarjeta................: " + ordenCompra.getNumeroTarjeta());
        logger.info("Productos.................: ");

        List<Producto> productoList = new ArrayList<>();
        for(int i = 0; i < ordenCompra.getProductos().size(); i++) {
            Producto producto = new Producto();
            producto.setCantidad(ordenCompra.getProductos().get(i).getCantidad());
            producto.setIdProducto(ordenCompra.getProductos().get(i).getIdProducto());
            productoList.add(producto);

            logger.info("     idProducto................: " + ordenCompra.getProductos().get(i).getIdProducto());
            logger.info("     cantidad..................: " + ordenCompra.getProductos().get(i).getCantidad());
            logger.info("     precioUnitario............: " + ordenCompra.getProductos().get(i).getPrecioUnitario());
            logger.info(" ");
        }
/*
        ServicioRecepcionStock servicioRecepcionStock = new ServicioRecepcionStockService().getServicioRecepcionStockPort();
        Reserva reserva = new Reserva();
        reserva.getProducto().addAll(productoList);
        uy.mgcoders.wsclient.stock.Resultado resultadoStock = servicioRecepcionStock.reservarProducto(reserva);

        logger.info("ResultadoStock  " + resultadoStock.getCodigo());


*/
        Resultado resultado = new Resultado();
        resultado.setCodigo("OK");
        resultado.setIdCompra(ordenCompra.getIdOrden());
        resultado.setDescripcion("descr...");

        logger.info("########## END --> Servicio Recepcion Orden de compra --> Ingresar Orden ##########");
        return resultado;
    }

}
