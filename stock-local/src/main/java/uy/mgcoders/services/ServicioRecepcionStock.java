package uy.mgcoders.services;

import uy.mgcoders.dto.Reserva;
import uy.mgcoders.dto.Resultado;
import uy.mgcoders.dto.ResultadoAnulacion;
import uy.mgcoders.ejb.ProcesarStockBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by pablo on 10/21/15.
 */
@Stateless
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ServicioRecepcionStock {

    @EJB
    ProcesarStockBean procesarStockBean;

    @WebMethod
    public Resultado reservarProducto(@WebParam(name = "productos") Reserva reserva) {

        Resultado resultado = procesarStockBean.procesarReserva(reserva);

        return resultado;
    }

    @WebMethod
    public ResultadoAnulacion anularReserva(@WebParam(name = "idReserva") long idReserva) {

        ResultadoAnulacion resultadoAnulacion = procesarStockBean.anularReserva(idReserva);

        return resultadoAnulacion;

    }

}
