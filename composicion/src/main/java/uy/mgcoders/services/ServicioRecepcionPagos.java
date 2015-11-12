package uy.mgcoders.services;

import uy.mgcoders.dto.Resultado;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by raul on 12/11/15.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ServicioRecepcionPagos {

    @WebMethod
    public ConfirmacionPago recepcionPago(@WebParam(name = "recepcionPago") RecepcionPago recepcionPago) {


        return null;
    }


}
