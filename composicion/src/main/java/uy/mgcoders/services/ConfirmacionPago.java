
package uy.mgcoders.services;



public class ConfirmacionPago {

    protected String idConfirmacionPago;
    protected String message;
    protected String status;

    public String getIdConfirmacionPago() {
        return idConfirmacionPago;
    }

    public void setIdConfirmacionPago(String idConfirmacionPago) {
        this.idConfirmacionPago = idConfirmacionPago;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
