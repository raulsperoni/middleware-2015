package org.fing.middleware.integration;

import org.fing.middleware.jms.DataLealtad;
import org.fing.middleware.jms.Producer;
import org.fing.middleware.services.ConfirmacionPago;
import org.fing.middleware.services.Pago;

import javax.jms.JMSException;
import java.util.*;

/**
 * Created by pablo on 10/09/15.
 */
public class RecepcionPagosAggregator {

    public List<ConfirmacionPago> confirmarPagos(Collection<Pago> pagos) {

        List<ConfirmacionPago> confirmacion = new ArrayList<ConfirmacionPago>();

        System.out.println("#################### confirmarPAgos ");
        for (Pago pago : pagos)
            try {

                Producer p = new Producer();
                //TODO: de donde saco el id del cliente aca?
                p.send(new DataLealtad(1, DataLealtad.Monto.USD, pago.getMonto(), new Date()));

                ConfirmacionPago c1 = new ConfirmacionPago();
                c1.setDescripcion("prueba agregaaa");
                c1.setIdentificadorPago(pago.getIdentificadorPago());
                c1.setResultado("resultado gateway");

                confirmacion.add(c1);

            } catch (JMSException e) {
                e.printStackTrace();
            }
        System.out.println("#################### confirmarPAgos ");


        return confirmacion;

    }


}
