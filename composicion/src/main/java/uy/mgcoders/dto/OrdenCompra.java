package uy.mgcoders.dto;

import java.util.List;

/**
 * Created by pablo on 10/22/15.
 */
public class OrdenCompra {

    private String idOrden;
    private long numeroTarjeta;
    private List<Producto> productos;

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
