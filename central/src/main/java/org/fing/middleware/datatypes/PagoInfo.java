package org.fing.middleware.datatypes;

import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.Pago;

import java.util.Date;

/**
 * Created by RSperoni on 15/09/2015.
 * Clase para transportar el pago junto a los datos que vienen en TransaccionPago, incluidos los datos del cliente.
 */
public class PagoInfo {

    protected Date fechaCobro;
    protected String formaPago;
    protected long identificadorCliente;
    protected long numeroSucursal;
    private Pago pago;
    private ConfirmacionPago confirmacionPago = null;

    public PagoInfo(Pago pago, Date fechaCobro, String formaPago, long identificadorCliente, long numeroSucursal) {
        this.pago = pago;
        this.fechaCobro = fechaCobro;
        this.formaPago = formaPago;
        this.identificadorCliente = identificadorCliente;
        this.numeroSucursal = numeroSucursal;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public long getIdentificadorCliente() {
        return identificadorCliente;
    }

    public void setIdentificadorCliente(long identificadorCliente) {
        this.identificadorCliente = identificadorCliente;
    }

    public long getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(long numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public ConfirmacionPago getConfirmacionPago() {
        return confirmacionPago;
    }

    public void setConfirmacionPago(ConfirmacionPago confirmacionPago) {
        this.confirmacionPago = confirmacionPago;
    }
}
