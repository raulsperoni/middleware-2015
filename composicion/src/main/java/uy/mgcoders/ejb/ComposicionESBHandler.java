package uy.mgcoders.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.OrdenCompra;
import uy.mgcoders.dto.Producto;
import uy.mgcoders.dto.Resultado;
import uy.mgcoders.wsclient.epuerto.ConfirmacionOrden;
import uy.mgcoders.wsclient.epuerto.ServicioEPuerto;
import uy.mgcoders.wsclient.epuerto.ServicioEPuertoService;
import uy.mgcoders.wsclient.pagosya.ConfirmacionPago;
import uy.mgcoders.wsclient.pagosya.RecepcionPago;
import uy.mgcoders.wsclient.pagosya.ServicioRecepcionPagos;
import uy.mgcoders.wsclient.pagosya.ServicioRecepcionPagosService;
import uy.mgcoders.wsclient.pubsub.AnularViaPS;
import uy.mgcoders.wsclient.pubsub.AnularViaPSService;
import uy.mgcoders.wsclient.stock.Reserva;
import uy.mgcoders.wsclient.stock.ServicioRecepcionStock;
import uy.mgcoders.wsclient.stock.ServicioRecepcionStockService;

import javax.ejb.Stateless;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

/**
 * Created by pablo on 11/11/15.
 */
@Stateless
public class ComposicionESBHandler {

    private static final Logger logger = LoggerFactory.getLogger(ComposicionESBHandler.class);


    public Resultado procesarOrdenCompra(OrdenCompra ordenCompra) {

        Resultado resultado = new Resultado();
        resultado.setIdCompra(ordenCompra.getIdOrden());
        try {

            uy.mgcoders.wsclient.stock.Resultado resultadoStockLocal = reservarProductosStockLocal(ordenCompra.getProductos());

            long idReserva;
            String servicio;

            if (resultadoStockLocal.getCodigo() != null && resultadoStockLocal.getCodigo().equalsIgnoreCase("OK")) {
                idReserva = resultadoStockLocal.getIdReserva();
                servicio = "stocklocal";
            } else {

                // TODO llamar a ePuerto - Pasar idOrden + lista productos.
                // TODO pasar idOrden para obtener id reserva
                servicio = "epuerto";
                ConfirmacionOrden confirmacionOrden = reservarProductosEPuerto(ordenCompra);
                idReserva = Integer.valueOf(confirmacionOrden.getIdentificadorReserva());
            }

            ConfirmacionPago confirmacionPago = pagarOrdenCompra(ordenCompra);

            if (confirmacionPago.getStatus() != null && confirmacionPago.getStatus().equalsIgnoreCase("true")) {
                resultado.setCodigo("OK");
                resultado.setDescripcion("Orden procesada correctamente");
            } else {
                anularReserva(idReserva, servicio);
                resultado.setCodigo("Error");
                resultado.setDescripcion(confirmacionPago.getMessage());
            }

        } catch (Exception e) {
            logger.error("Mensaje: " + e.getMessage());
            resultado.setCodigo("Error");
            resultado.setDescripcion("Error inesperado al procesar la orden.");
        }

        return resultado;
    }

    private uy.mgcoders.wsclient.stock.Resultado reservarProductosStockLocal(List<Producto> productos) {
        uy.mgcoders.wsclient.stock.Resultado resultadoStock;
        try {
            // Crear la lista de procutos para invocar a stock local.
            List<uy.mgcoders.wsclient.stock.Producto> productoList = new ArrayList<>();
            for(Producto p : productos) {
                uy.mgcoders.wsclient.stock.Producto producto = new uy.mgcoders.wsclient.stock.Producto();
                producto.setCantidad(p.getCantidad());
                producto.setIdProducto(p.getIdProducto());
                productoList.add(producto);
            }

            ServicioRecepcionStock servicioRecepcionStock = new ServicioRecepcionStockService().getServicioRecepcionStockPort();
            Reserva reserva = new Reserva();
            reserva.getProducto().addAll(productoList);
            resultadoStock = servicioRecepcionStock.reservarProducto(reserva);
        } catch (Exception e) {
            logger.error("Error al invocar el servicio de stock-local");
            logger.error("Mensaje: " + e.getMessage());

            resultadoStock = new uy.mgcoders.wsclient.stock.Resultado();
            resultadoStock.setCodigo("Error");
            resultadoStock.setDescripcion("Error al invocar el servicio de stock-local");
        }
        return resultadoStock;
    }

    private ConfirmacionOrden reservarProductosEPuerto(OrdenCompra ordenCompra) {
        ConfirmacionOrden confirmacionOrden;
        try {

            // TODO cambiar esta invocacion para enviar una lista de los productos!!!!

            ServicioEPuerto servicioEPuerto = new ServicioEPuertoService().getServicioEPuertoPort();
            confirmacionOrden = servicioEPuerto.colocarOrden(ordenCompra.getIdOrden(), 15, 12);

        } catch (Exception e) {
            logger.error("Error al invocar el servicio de ePuerto");
            logger.error("Mensaje: " + e.getMessage());

            confirmacionOrden = new ConfirmacionOrden();
            confirmacionOrden.setIdentificadorCompra(ordenCompra.getIdOrden());
            confirmacionOrden.setCodigoResultado(1);
            confirmacionOrden.setDescripcionResultado("Error al invocar el servicio de ePuerto");

        }
        return confirmacionOrden;
    }

    private ConfirmacionPago pagarOrdenCompra(OrdenCompra ordenCompra) {
        ConfirmacionPago confirmacionPago;
        try {
            // Calcular el total de la compra.
            double montoTotal = 0;
            for(Producto p : ordenCompra.getProductos()) {
                montoTotal += p.getCantidad() * p.getPrecioUnitario();
            }

            // Pasa la fecha al formato adecuado.
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(new Date());
            XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

            ServicioRecepcionPagos servicioRecepcionPagos = new ServicioRecepcionPagosService().getServicioRecepcionPagosPort();

            RecepcionPago recepcionPago = new RecepcionPago();
            recepcionPago.setFecha(fecha);
            recepcionPago.setIdCompra(ordenCompra.getIdOrden());
            recepcionPago.setMonto(montoTotal);
            recepcionPago.setNumeroTarjeta(ordenCompra.getNumeroTarjeta());
            confirmacionPago = servicioRecepcionPagos.recepcionPago(recepcionPago);
        } catch (Exception e) {
            logger.error("Error al invocar el servicio de pagos-ya");
            logger.error("Mensaje: " + e.getMessage());

            confirmacionPago = new ConfirmacionPago();
            confirmacionPago.setStatus("false");
            confirmacionPago.setMessage("Error al invocar el servicio de pagos-ya");
        }
        return confirmacionPago;
    }

    private void anularReserva(long idReserva, String servicio) {
        try {
            AnularViaPS anularViaPS = new AnularViaPSService().getAnularViaPSPort();
            String response = anularViaPS.anularReservas(idReserva, servicio);
        } catch (Exception e) {
            logger.error("Error al invocar el servicio de anular reserva");
            logger.error("Mensaje: " + e.getMessage());
        }
    }

}
