package org.fing.middleware.services;

/**
 * Created by cbauza on 14/09/2015.
 */

public class WSResult {

    private boolean status;
    private String mensaje;
    private long idCobro;

    public WSResult(){}

    public WSResult(boolean status, String mensaje, long idCobro){
        this.status = status;
        this.mensaje = mensaje;
        this.idCobro = idCobro;
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
}
