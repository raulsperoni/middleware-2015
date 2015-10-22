package uy.mgcoders.services;

import uy.mgcoders.dto.Resultado;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Created by pablo on 10/21/15.
 */
@Stateless
@WebService
public class RecepcionStock {

    public Resultado reservarProducto() {
        return new Resultado();
    }

}
