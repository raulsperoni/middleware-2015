package uy.mgcoders.services;

import org.jboss.ws.api.annotation.EndpointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.OrdenCompra;
import uy.mgcoders.wsclient.stock.Producto;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
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
@WebService() // wsdlLocation = "ServicioRecepcionOrdenCompra.wsdl"
@Addressing(enabled = true, required = true)
//@HandlerChain(file = "soaphandler.xml")
/*@EndpointProperties(value = {
        @EndpointProperty(key = "ws-security.signature.properties", value = "serversecurity.properties"),
        @EndpointProperty(key = "ws-security.encryption.properties", value = "serversecurity.properties"),
        @EndpointProperty(key = "ws-security.signature.username", value = "server"),
        @EndpointProperty(key = "ws-security.encryption.username", value = "client"),
        @EndpointProperty(key = "ws-security.callback-handler", value = "uy.mgcoders.security.KeystorePasswordCallback")
    }
)*/
//@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
@org.apache.cxf.interceptor.OutInterceptors(interceptors = {"uy.mgcoders.handler.MessageChangeInterceptor"})
public class ServicioRecepcionOrdenCompra {

    @Resource
    WebServiceContext context;

    private static final Logger logger = LoggerFactory.getLogger(ServicioRecepcionOrdenCompra.class);

    @WebMethod
    public void ingresarOrden(@WebParam(name = "ordencompra") OrdenCompra ordenCompra) {

        logger.info("metodo: ingresarOrden");
        logger.info("idOrden...................: " + ordenCompra.getIdOrden());
        logger.info("nroTargeta................: " + ordenCompra.getNumeroTarjeta());
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
/*
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
      servicioCallback.confirmarOrden(resultado);

        Resultado resultado = new Resultado();
        resultado.setCodigo("OK");
        resultado.setIdCompra(ordenCompra.getIdOrden());
        resultado.setDescripcion("descr...");


        Message message = PhaseInterceptorChain.getCurrentMessage();
        message.clear();
        message.setContent(Resultado.class, resultado);
*/


        logger.info("#######");
    }

}
