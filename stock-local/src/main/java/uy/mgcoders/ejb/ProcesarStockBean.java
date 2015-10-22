package uy.mgcoders.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.Producto;
import uy.mgcoders.dto.Reserva;
import uy.mgcoders.dto.Resultado;
import uy.mgcoders.dto.ResultadoAnulacion;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.HashMap;

/**
 * Created by pablo on 10/21/15.
 */
@Singleton
public class ProcesarStockBean {

    private static final Logger logger = LoggerFactory.getLogger(ProcesarStockBean.class);

    private long idReserva;
    private HashMap<Long, Reserva> reservas;

    @PostConstruct
    public void init() {
        idReserva = 0;
        reservas = new HashMap<>();
    }

    public Resultado procesarReserva(Reserva reserva) {
        idReserva++;
        reservas.put(idReserva, reserva);

        logger.info("metodo: anularReserva");
        for(Producto p : reserva.getProductos()) {
            logger.info("Identificador de producto: " + p.getIdProducto());
            logger.info("Cantidad.................: " + p.getCantidad());
        }
        logger.info("idReserva....................:" + idReserva);
        logger.info("#######");

        Resultado resultado = new Resultado();
        resultado.setCodigo("OK");
        resultado.setDescripcion("descr");
        resultado.setIdReserva(idReserva);

        return resultado;
    }

    public ResultadoAnulacion anularReserva(long idReserva) {
        ResultadoAnulacion resultadoAnulacion = new ResultadoAnulacion();
        if(reservas.containsKey(idReserva)) {
            reservas.remove(idReserva);
            resultadoAnulacion.setCodigo("OK");
            resultadoAnulacion.setDescripcion("Reserva anulada " + idReserva);
        }
        else {
            resultadoAnulacion.setCodigo("Error");
            resultadoAnulacion.setDescripcion("No existe reserva con id: " + idReserva);
        }

        logger.info("metodo: anularReserva");
        logger.info("Identificador de reserva:" + idReserva);
        logger.info("Código..................:" + resultadoAnulacion.getCodigo());
        logger.info("#######");

        return resultadoAnulacion;
    }


}
