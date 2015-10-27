package uy.mgcoders.dto;

/**
 * Created by pablo on 10/22/15.
 */
public class Resultado {



    private boolean status;

    private String mensaje;

    private long idConfirmacionPago;



    public Resultado() {
    }

    public Resultado(boolean status, String mensaje, long idConfirmacionPago) {
        this.status = status;
        this.mensaje = mensaje;
        this.idConfirmacionPago = idConfirmacionPago;
    }



    public long getIdConfirmacionPago() {
        return idConfirmacionPago;
    }

    public void setIdConfirmacionPago(long idConfirmacionPago) {

        this.idConfirmacionPago = idConfirmacionPago;
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
}
