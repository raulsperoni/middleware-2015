package uy.mgcoders.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.Pago;
import uy.mgcoders.dto.Resultado;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by pablo on 10/21/15.
 */
@Singleton
public class ProcesarPagosBean {

    private long idConfirmacionPago;
    private static final Logger logger = LoggerFactory.getLogger(ProcesarPagosBean.class);
    private HashMap<Long, Pago> pagos;

    @PostConstruct
    public void init() {
        idConfirmacionPago = 0;
        pagos = new HashMap<>();
        logger.info("Se inicializa idConfirmacionPago: " + idConfirmacionPago);
    }

    public Resultado procesarPago(Pago pago) {

        logger.info("metodo: procesarPago");
        logger.info("Identificador de compra..: " + pago.getIdCompra());
        logger.info("Numero tarjeta de credito: " + pago.getNumeroTarjeta());
        logger.info("Monto....................: " + pago.getMonto());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        logger.info("Fecha y hora.............: " + sdf.format(pago.getFecha().getTime()) );

        Resultado resultado = new Resultado();
        // Si ya hay un pago para la orden se retorna error. {"idConfirmacionPago":-1}
        if(pagos.containsKey(pago.getIdCompra())) {

            resultado.setIdConfirmacionPago(-1);
            resultado.setStatus(false);
            resultado.setMensaje("Ya existe un pago con el identificador de compra " + pago.getIdCompra());

            logger.info("Confirmacion de pago.....: ERROR");
        }
        else { // En caso de exito se retorna la identificacion del pago.
            idConfirmacionPago++;
            pagos.put(pago.getIdCompra(), pago);

            resultado.setIdConfirmacionPago(idConfirmacionPago);
            resultado.setStatus(true);
            resultado.setMensaje("");

            logger.info("Confirmacion de pago.....: " + idConfirmacionPago);
        }

        logger.info("#######");

        return resultado;
    }
}
