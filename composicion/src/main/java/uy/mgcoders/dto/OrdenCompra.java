package uy.mgcoders.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by pablo on 10/22/15.
 */
@XmlType(propOrder = {"idOrden","numeroTarjeta","productos"})
@XmlRootElement(name = "OrdenCompra")
public class OrdenCompra {

    private String idOrden;
    private long numeroTarjeta;
    private List<Producto> productos;

    public String getIdOrden() {
        return idOrden;
    }

    @XmlElement(required = true)
    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    @XmlElement(required = true)
    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @XmlElement(required = true)
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
