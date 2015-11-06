package uy.mgcoders.services;

import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.wsclient.callback.Resultado;
import uy.mgcoders.wsclient.callback.ServicioCallback;
import uy.mgcoders.wsclient.callback.ServicioCallbackService;
import uy.mgcoders.dto.OrdenCompra;
import uy.mgcoders.wsclient.stock.Producto;
import uy.mgcoders.wsclient.stock.Reserva;
import uy.mgcoders.wsclient.stock.ServicioRecepcionStock;
import uy.mgcoders.wsclient.stock.ServicioRecepcionStockService;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
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
//@HandlerChain(file = "soaphandler.xml")
@org.apache.cxf.interceptor.OutInterceptors(interceptors = {"uy.mgcoders.handler.MyInterceptor"})
public class ServicioRecepcionOrdenCompra {

    @Resource
    WebServiceContext context;

    private static final Logger logger = LoggerFactory.getLogger(ServicioRecepcionOrdenCompra.class);

    @WebMethod
    public void ingresarOrden(@WebParam(name = "ordencompra") OrdenCompra ordenCompra) {

        logger.info("metodo: ingresarOrden");
        logger.info("idOrden...................: " + ordenCompra.getIdOrden());
        logger.info("nroTerfeta................: " + ordenCompra.getNumeroTarjeta());
        logger.info("Productos................: ");

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

        ServicioRecepcionStock servicioRecepcionStock = new ServicioRecepcionStockService().getServicioRecepcionStockPort();
        Reserva reserva = new Reserva();
        reserva.getProducto().addAll(productoList);
        uy.mgcoders.wsclient.stock.Resultado resultadoStock = servicioRecepcionStock.reservarProducto(reserva);

        logger.info("ResultadoStock  " + resultadoStock.getCodigo());


        MessageContext messageContext = context.getMessageContext();
        AddressingProperties addressProp = (AddressingProperties) messageContext.get(JAXWSAConstants.ADDRESSING_PROPERTIES_INBOUND);
        EndpointReferenceType eprType = addressProp.getReplyTo();
        logger.info("444444444444 " + eprType.getAddress().getValue());
        ServicioCallback servicioCallback = new ServicioCallbackService().getServicioCallbackPort();
        ((BindingProvider)servicioCallback).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, eprType.getAddress().getValue());


        Resultado resultado = new Resultado();
        resultado.setCodigo("OK");
        resultado.setIdCompra(ordenCompra.getIdOrden());
        resultado.setDescripcion("descr...");
        servicioCallback.confirmarOrden(resultado);

        logger.info("#######");
    }

}
