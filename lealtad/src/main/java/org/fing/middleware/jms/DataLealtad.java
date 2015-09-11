package org.fing.middleware.jms;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by raul on 06/09/15.
 */
public class DataLealtad implements Serializable {

    protected long identificadorCliente;
    protected Monto codigoMoneda;
    protected double monto;
    protected Date fechaCobro;

    public DataLealtad(long identificadorCliente, Monto codigoMoneda, double monto, Date fechaCobro) {
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

    public Monto getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(Monto codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public enum Monto {
        UYU, USD
    }
}
