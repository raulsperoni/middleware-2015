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
        logger.info("Se inicializa idReserva: " + idReserva);
    }

    public Resultado procesarReserva(Reserva reserva) {
        logger.info("metodo: procesarReserva");

        long max = 0;
        boolean errorNegativo = false;
        for(Producto p : reserva.getProductos()) {
            logger.info("Identificador de producto: " + p.getIdProducto());
            logger.info("Cantidad.................: " + p.getCantidad());

            max = (p.getCantidad() > max) ? p.getCantidad() : max;
            if(p.getIdProducto() < 0) {
                errorNegativo = true;
            }
        }

        /**
         * Si alguno de los productos tiene cantidad mayor a 100 o alguno de los id es menor a cero
         * retorna error en el stock.
         * */

        Resultado resultado = new Resultado();
        if(max > 100 || errorNegativo) {
            resultado.setCodigo("Error");
            resultado.setDescripcion(errorNegativo ? "Producto con id negativo" : "Producto con cantidad mayor a 100");

            logger.info("idReserva....................: ERROR");
        }
        else {
            idReserva++;
            reservas.put(idReserva, reserva);
            resultado.setCodigo("OK");
            resultado.setDescripcion("Productos reservados correctamente");
            resultado.setIdReserva(idReserva);

            logger.info("idReserva....................:" + idReserva);
        }
        logger.info("#######");

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
