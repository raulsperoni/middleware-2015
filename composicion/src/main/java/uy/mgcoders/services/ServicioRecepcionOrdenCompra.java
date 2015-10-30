package uy.mgcoders.services;

import com.sun.xml.ws.api.SOAPVersion;
import com.sun.xml.ws.api.addressing.AddressingVersion;
import com.sun.xml.ws.api.addressing.WSEndpointReference;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.callback.Resultado;
import uy.mgcoders.callback.ServicioCallback;
import uy.mgcoders.callback.ServicioCallbackService;
import uy.mgcoders.dto.OrdenCompra;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.Addressing;

/**
 * Created by pablo on 10/22/15.
 */

// TODO esto esta pendiente todo!

@Stateless
@WebService
@Addressing(enabled = true, required = true)
@HandlerChain(file = "soaphandler.xml")
@org.apache.cxf.interceptor.OutInterceptors(interceptors = {"uy.mgcoders.handler.MyInterceptor"})
public class ServicioRecepcionOrdenCompra {

    @Resource
    WebServiceContext context;

    private static final Logger logger = LoggerFactory.getLogger(ServicioRecepcionOrdenCompra.class);

    @WebMethod
    public void ingresarOrden(@WebParam(name = "ordencompra") OrdenCompra ordenCompra) {
/*
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.info("InterruptedException ");
        }*/


        logger.info("metodo: ingresarOrden");
        logger.info("idOrden...................: " + ordenCompra.getIdOrden());
        logger.info("nroTerfeta................: " + ordenCompra.getNumeroTarjeta());
        logger.info("Productos................: ");
        for(int i = 0; i < ordenCompra.getProductos().size(); i++) {
            logger.info("     idProducto................: " + ordenCompra.getProductos().get(i).getIdProducto());
            logger.info("     cantidad..................: " + ordenCompra.getProductos().get(i).getCantidad());
            logger.info("     precioUnitario............: " + ordenCompra.getProductos().get(i).getPrecioUnitario());
            logger.info(" ");
        }

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


/*
        HeaderList hl =
                (HeaderList)context.getMessageContext().get(
                        JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);

        // gets the addressing informations in the SOAP header
        WSEndpointReference reference = hl.getReplyTo(AddressingVersion.W3C,
                SOAPVersion.SOAP_11);
        logger.info("aa " + reference.getAddress());*/
        // TODO: No habria que retornar un bool que indique si la confirmacion de la orden?

        logger.info("#######");
    }

}
