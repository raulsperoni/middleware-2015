package uy.mgcoders.services;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)

public class ServicePagoYa {
	
	@WebMethod
	public String recepcionPago(int idCompra, int numeroTarjeta, int monto, Date fecha){
		return "ok";
	}


}
