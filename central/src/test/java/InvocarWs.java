import org.junit.Test;
import otherservices.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by raul on 15/09/15.
 */
public class InvocarWs {

    @Test
    public void pagoFacturas() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(22);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2222);
        p.setMonto(2345);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }
    }

    @Test
    public void pagoOffline() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(22);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2222);
        p.setMonto(2345);
        p.setNombreGestion("Offline");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }
    }
}
