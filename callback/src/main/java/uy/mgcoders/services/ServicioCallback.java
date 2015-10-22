package uy.mgcoders.services;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by pablo on 10/22/15.
 */
@Stateless
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ServicioCallback {
}
