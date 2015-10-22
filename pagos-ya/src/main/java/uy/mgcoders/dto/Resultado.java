package uy.mgcoders.dto;

/**
 * Created by pablo on 10/22/15.
 */
public class Resultado {

    private long idConfirmacionPago;

    public Resultado() {
    }

    public Resultado(long idConfirmacionPago) {
        this.idConfirmacionPago = idConfirmacionPago;
    }

    public long getIdConfirmacionPago() {
        return idConfirmacionPago;
    }

    public void setIdConfirmacionPago(long idConfirmacionPago) {
        this.idConfirmacionPago = idConfirmacionPago;
    }
}
