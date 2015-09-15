import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.fing.middleware.services.*;
import org.junit.Test;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by raul on 15/09/15.
 */
public class InvocarWs {

    @Test
    public void invocarWs() {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        XMLGregorianCalendar xmlGregorianCalendar = new XMLGregorianCalendarImpl();
        xmlGregorianCalendar.setDay(23);
        xmlGregorianCalendar.setMonth(10);
        xmlGregorianCalendar.setYear(2015);
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
}
