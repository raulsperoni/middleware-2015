package org.fing.middleware.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbauza on 14/09/2015.
 */

public class WSResult {

    private boolean status;
    private String mensaje;
    private long idCobro;
    private List<String> lCodigosEntrada;

    public WSResult(){
        this.lCodigosEntrada = new ArrayList<String>();
    }

    public WSResult(boolean status, String mensaje, long idCobro, List<String> lCodigosEntrada){
        this.status = status;
        this.mensaje = mensaje;
        this.idCobro = idCobro;
        this.lCodigosEntrada = lCodigosEntrada;
    }

    @Override
    public String toString(){

        String codsEntradas = "";
        for(int i = 0; i < lCodigosEntrada.size(); i++)
            codsEntradas += lCodigosEntrada.get(i) + "-";

        return status + ";" + mensaje + ";" + idCobro + ";" + codsEntradas + "\r\n";
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(long idCobro) {
        this.idCobro = idCobro;
    }

    public List<String> getlCodigosEntrada() {
        return lCodigosEntrada;
    }

    public void setlCodigosEntrada(List<String> lCodigosEntrada) {
        this.lCodigosEntrada = lCodigosEntrada;
    }
}
