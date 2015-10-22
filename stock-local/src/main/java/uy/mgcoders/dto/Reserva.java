package uy.mgcoders.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by pablo on 10/21/15.
 */
@XmlType(propOrder = {"productos"})
@XmlRootElement(name = "Reserva")
public class Reserva {

    private List<Producto> productos;

    public List<Producto> getProductos() {
        return productos;
    }

    @XmlElement(name = "producto")
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
