package org.fing.middleware.beans;


import org.fing.middleware.jms.DataLealtad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 09/09/15.
 */
public class Usuario {

    private final long identificadorUsuario;
    private int puntos = 0;
    private List<DataLealtad> listaPagos = new ArrayList<DataLealtad>();

    public Usuario(long identificadorUsuario) {
        this.identificadorUsuario = identificadorUsuario;
    }

    public void addPago(DataLealtad d) {
        this.listaPagos.add(d);
        puntos += calcularPuntos(d);

    }

    public int calcularPuntos(DataLealtad d) {
        switch (d.getCodigoMoneda()) {
            case UYU:
                return (int) d.getMonto() / 100;
            case USD:
                return (int) d.getMonto() / 10;
            default:
                return (int) d.getMonto() / 100;
        }
    }

    public int getPuntos() {
        return puntos;
    }

    public List<DataLealtad> getListaPagos() {
        return listaPagos;
    }

    public long getIdentificadorUsuario() {
        return identificadorUsuario;
    }
}
