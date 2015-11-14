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

        logger.info("########## START --> Servicio Stock Local --> Procesar Reserva ##########");

        Resultado resultado = new Resultado();

        try {
            // Recorremos todos los productos solicitados en la reserva.
            for (Producto p : reserva.getProductos()) {

                logger.info("Identificador de producto: " + p.getIdProducto());
                logger.info("Cantidad.................: " + p.getCantidad());

                // Chequeamos que la cantidad sea mayor que 0.
                if (p.getCantidad() <= 0)
                    throw new Exception("Se ha ingresado una cantidad no valida (" + p.getCantidad() +
                            ") para el producto " + p.getIdProducto());

                // Chequeamos que el id de producto sea mayor o igual que 0.
                if (p.getIdProducto() < 0)
                    throw new Exception("El identificador del producto (" + p.getIdProducto() +
                            ") no puede ser negativo.");

                // Chequeamos que no venga una cantidad mayor a 100.
                if (p.getCantidad() > 100)
                    throw new Exception("No puede realizar una reserva para el producto " +
                            p.getIdProducto() + " con cantidad mayor a 100.");
            }

            idReserva++;
            resultado.setCodigo("OK");
            resultado.setDescripcion("Productos reservados correctamente.");
            resultado.setIdReserva(idReserva);
            reservas.put(idReserva, reserva);

            logger.info("OK procesarReserva");
            logger.info("idReserva....................: " + idReserva);
        }
        catch (Exception ex){ // Si ocurre algun error retornamos "Error" y el mensaje correspondiente.
            logger.info("ERROR procesarReserva");
            logger.info("Mensaje....................: " + ex.getMessage());
            resultado.setCodigo("Error");
            resultado.setDescripcion("ERROR: " + ex.getMessage());
            resultado.setIdReserva(-1);
        }

        logger.info("########## END --> Servicio Stock Local --> Procesar Reserva ##########");

        return resultado;
    }

    public ResultadoAnulacion anularReserva(long idReserva) {

        logger.info("########## START --> Servicio Stock Local --> Anular Reserva ##########");

        ResultadoAnulacion resultadoAnulacion = new ResultadoAnulacion();

        try {

            if(!reservas.containsKey(idReserva))
                throw new Exception("No existe una reserva con idReserva " + idReserva);

            reservas.remove(idReserva);
            resultadoAnulacion.setCodigo("OK");
            resultadoAnulacion.setDescripcion("Reserva anulada " + idReserva);

            logger.info("OK anularReserva");
            logger.info("Identificador de reserva:" + idReserva);
            logger.info("Código..................:" + resultadoAnulacion.getCodigo());
        }
        catch (Exception ex)
        {
            logger.info("ERROR anularReserva");
            logger.info("Mensaje....................: " + ex.getMessage());
            resultadoAnulacion.setCodigo("Error");
            resultadoAnulacion.setDescripcion("ERROR: " + ex.getMessage());
        }

        logger.info("########## START --> Servicio Stock Local --> Anular Reserva ##########");

        return resultadoAnulacion;
    }
}
