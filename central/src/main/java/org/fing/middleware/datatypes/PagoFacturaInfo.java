package org.fing.middleware.datatypes;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

/**
 * Created by RSperoni on 15/09/2015.
 */
public class PagoFacturaInfo {

    private long idFactura;
    private short codMoneda;
    private BigDecimal monto;
    private XMLGregorianCalendar fechaHoraCobro;

    public PagoFacturaInfo(long idFactura, short codMoneda, BigDecimal monto, XMLGregorianCalendar fechaHoraCobro) {
        this.idFactura = idFactura;
        this.codMoneda = codMoneda;
        this.monto = monto;
        this.fechaHoraCobro = fechaHoraCobro;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }

    public short getCodMoneda() {
        return codMoneda;
    }

    public void setCodMoneda(short codMoneda) {
        this.codMoneda = codMoneda;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public XMLGregorianCalendar getFechaHoraCobro() {
        return fechaHoraCobro;
    }

    public void setFechaHoraCobro(XMLGregorianCalendar fechaHoraCobro) {
        this.fechaHoraCobro = fechaHoraCobro;
    }
}
