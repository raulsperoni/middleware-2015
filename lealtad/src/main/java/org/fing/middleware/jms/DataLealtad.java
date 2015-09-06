package org.fing.middleware.jms;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;

/**
 * Created by raul on 06/09/15.
 */
public class DataLealtad implements Serializable {

    protected long identificadorCliente;
    protected String codigoMoneda;
    protected double monto;
    protected XMLGregorianCalendar fechaCobro;

    public DataLealtad(long identificadorCliente, String codigoMoneda, double monto, XMLGregorianCalendar fechaCobro) {
        this.identificadorCliente = identificadorCliente;
        this.codigoMoneda = codigoMoneda;
        this.monto = monto;
        this.fechaCobro = fechaCobro;
    }

    public long getIdentificadorCliente() {
        return identificadorCliente;
    }

    public void setIdentificadorCliente(long identificadorCliente) {
        this.identificadorCliente = identificadorCliente;
    }

    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public XMLGregorianCalendar getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(XMLGregorianCalendar fechaCobro) {
        this.fechaCobro = fechaCobro;
    }
}
