import org.junit.Test;
import otherservices.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by raul on 15/09/15.
 */
public class InvocarWs {

    @Test
    public void pagoFacturas_Invalida() throws DatatypeConfigurationException {
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

        assertEquals(confirmacionTransaccion.getConfirmacion().get(0).getResultado(), "Error");
    }

    @Test
    public void pagoFacturas_Valida() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(45293204);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(50);
        p.setMonto(2345);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals(confirmacionTransaccion.getConfirmacion().get(0).getResultado(), "OK");
    }

    @Test
    public void pagoFacturas_Moneda_Invalida() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(45293204);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("CAN");
        p.setIdentificadorPago(50);
        p.setMonto(2345);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals(confirmacionTransaccion.getConfirmacion().get(0).getResultado(), "Error");
    }

    @Test
    public void pagoEntradas_Invalido() throws DatatypeConfigurationException {
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
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("-5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals(confirmacionTransaccion.getConfirmacion().get(0).getResultado(), "Error");
    }

    @Test
    public void pagoEntradas_Valido() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(31503944);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2);
        p.setMonto(150);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("3"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals(confirmacionTransaccion.getConfirmacion().get(0).getResultado(), "OK");
    }

    @Test
    public void pagoEntradas_Valido_Dolares() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(31503944);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("USD");
        p.setIdentificadorPago(2);
        p.setMonto(15);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals(confirmacionTransaccion.getConfirmacion().get(0).getResultado(), "OK");
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

    @Test
    public void pagoAll() throws DatatypeConfigurationException {
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
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);

        p = new Pago();
        p.setCodigoMoneda("USD");
        p.setIdentificadorPago(2222);
        p.setMonto(134);
        p.setNombreGestion("Offline");
        transaccionPago.getPagos().add(p);


        p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2222);
        p.setMonto(1069);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);


        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }
    }

}
