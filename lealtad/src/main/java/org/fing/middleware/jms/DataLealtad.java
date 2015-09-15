package org.fing.middleware.jms;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by raul on 06/09/15.
 */
public class DataLealtad implements Serializable {

    protected long identificadorCliente;
    protected Moneda codigoMoneda;
    protected double monto;
    protected Date fechaCobro;

    public DataLealtad(long identificadorCliente, Moneda codigoMoneda, double monto, Date fechaCobro) {
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

    public Moneda getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(Moneda codigoMoneda) {
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

    public enum Moneda {
        UYU, USD
    }
}
