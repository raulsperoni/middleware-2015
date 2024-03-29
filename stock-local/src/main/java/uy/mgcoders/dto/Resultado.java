package uy.mgcoders.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by pablo on 10/21/15.
 */
@XmlType(propOrder = {"codigo","descripcion","idReserva"})
@XmlRootElement
public class Resultado {

    private String codigo;
    private String descripcion;
    private long idReserva;

    public Resultado() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }
}
