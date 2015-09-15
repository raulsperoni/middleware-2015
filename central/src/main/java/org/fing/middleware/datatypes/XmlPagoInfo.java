package org.fing.middleware.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by raul on 15/09/15.
 */
@XmlRootElement
public class XmlPagoInfo {


    private long identificadorCliente;
    private String codigoMoneda;
    private BigDecimal monto;
    private String fechaCobro;
    private String horaCobro;

    public XmlPagoInfo() {
    }

    public XmlPagoInfo(long identificadorCliente, String codigoMoneda, BigDecimal monto, String fechaCobro, String horaCobro) {
        this.identificadorCliente = identificadorCliente;
        this.codigoMoneda = codigoMoneda;
        this.monto = monto;
        this.fechaCobro = fechaCobro;
        this.horaCobro = horaCobro;
    }

    @XmlElement
    public long getIdentificadorCliente() {
        return identificadorCliente;
    }

    public void setIdentificadorCliente(long identificadorCliente) {
        this.identificadorCliente = identificadorCliente;
    }

    @XmlElement
    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    @XmlElement
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @XmlElement
    public String getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    @XmlElement
    public String getHoraCobro() {
        return horaCobro;
    }

    public void setHoraCobro(String horaCobro) {
        this.horaCobro = horaCobro;
    }
}
