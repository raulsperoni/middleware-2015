import com.sun.xml.ws.fault.ServerSOAPFaultException;
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
public class InvocarWsTest {

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
        transaccionPago.setIdentificadorCliente(1);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2222);
        p.setMonto(1500);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("Error", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
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
        transaccionPago.setIdentificadorCliente(1);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(50);
        p.setMonto(1500);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
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
        transaccionPago.setIdentificadorCliente(2);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("CAN");
        p.setIdentificadorPago(50);
        p.setMonto(100);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("Error", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
    }

    @Test
    public void pagoFacturas_Dolares() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(2);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("USD");
        p.setIdentificadorPago(75);
        p.setMonto(100);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
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
        transaccionPago.setIdentificadorCliente(2);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2222);
        p.setMonto(100);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("-5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("Error", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
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
        transaccionPago.setIdentificadorCliente(2);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2);
        p.setMonto(900);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("3"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
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
        transaccionPago.setIdentificadorCliente(1);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("USD");
        p.setIdentificadorPago(2);
        p.setMonto(100);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("30"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
    }

    @Test
    public void pagoEntradas_Cantidad_De_Entradas_No_Disponible() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(1);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(2);
        p.setMonto(1000);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("150"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("Error", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
    }

    @Test
    public void pagoEntradas_Moneda_No_Valida() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(1);
        transaccionPago.setNumeroSucursal(234);
        Pago p = new Pago();
        p.setCodigoMoneda("PIS");
        p.setIdentificadorPago(2);
        p.setMonto(1000);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);
        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
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
        transaccionPago.setIdentificadorCliente(3);
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

        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
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
        transaccionPago.setIdentificadorCliente(3);
        transaccionPago.setNumeroSucursal(234);

        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(150);
        p.setMonto(100);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);

        p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(150);
        p.setMonto(100);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("-10"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);

        p = new Pago();
        p.setCodigoMoneda("USD");
        p.setIdentificadorPago(151);
        p.setMonto(100);
        p.setNombreGestion("Offline");
        transaccionPago.getPagos().add(p);


        p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(66);
        p.setMonto(100);
        p.setNombreGestion("Facturas");
        transaccionPago.getPagos().add(p);


        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(0).getResultado());
        assertEquals("Error", confirmacionTransaccion.getConfirmacion().get(1).getResultado());
        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(2).getResultado());
        assertEquals("OK", confirmacionTransaccion.getConfirmacion().get(3).getResultado());
    }

    @Test(expected = ServerSOAPFaultException.class)
    public void pagoAll_Fecha_Invalida() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        //Sin fecha
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(3);
        transaccionPago.setNumeroSucursal(234);

        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(150);
        p.setMonto(100);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);


        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

    }

    @Test(expected = ServerSOAPFaultException.class)
    public void pagoAll_FormaPago_Invalida() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("LALALLALAL");
        transaccionPago.setIdentificadorCliente(3);
        transaccionPago.setNumeroSucursal(234);

        Pago p = new Pago();
        p.setCodigoMoneda("UYU");
        p.setIdentificadorPago(150);
        p.setMonto(100);
        p.setNombreGestion("Entradas");
        p.getDatoAdicional().add("5"); // Cantidad de entradas
        transaccionPago.getPagos().add(p);


        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

    }

    @Test(expected = ServerSOAPFaultException.class)
    public void Borde_sin_pagos() throws DatatypeConfigurationException {
        ServicioRecepcionPagosService servicioRecepcionPagosService = new ServicioRecepcionPagosService();
        TransaccionPago transaccionPago = new TransaccionPago();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gc);
        transaccionPago.setFechaCobro(xmlGregorianCalendar);
        transaccionPago.setFormaPago("Efectivo");
        transaccionPago.setIdentificadorCliente(3);
        transaccionPago.setNumeroSucursal(234);


        ConfirmacionTransaccion confirmacionTransaccion = servicioRecepcionPagosService.getServicioRecepcionPagosPort().recepcionPagos(transaccionPago);
        for (ConfirmacionPago confirmacionPago : confirmacionTransaccion.getConfirmacion()) {
            System.out.println("CONFIRMACION PAGO: " + confirmacionPago.getResultado());
        }

    }

}
