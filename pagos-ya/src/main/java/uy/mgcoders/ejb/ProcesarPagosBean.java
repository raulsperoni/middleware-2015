package uy.mgcoders.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.Pago;
import uy.mgcoders.dto.Resultado;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.text.SimpleDateFormat;

/**
 * Created by pablo on 10/21/15.
 */
@Singleton
public class ProcesarPagosBean {

    private long idConfirmacionPago;
    private static final Logger logger = LoggerFactory.getLogger(ProcesarPagosBean.class);

    @PostConstruct
    public void init() {
        idConfirmacionPago = 0;
        logger.info("Se inicializa idConfirmacionPago: " + idConfirmacionPago);
    }

    public Resultado procesarPago(Pago pago) {
        logger.info("metodo: procesarPago");
        logger.info("Identificador de compra..: " + pago.getIdCompra());
        logger.info("Numero tarjeta de credito: " + pago.getNumeroTarjeta());
        logger.info("Monto....................: " + pago.getMonto());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        logger.info("Fecha y hora.............: " + sdf.format(pago.getFecha().getTime()) );

        idConfirmacionPago++;

        logger.info("Confirmacion de pago.....: " + idConfirmacionPago);
        logger.info("#######");

        return new Resultado(idConfirmacionPago);
    }

}
