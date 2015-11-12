package uy.mgcoders.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.mgcoders.dto.OrdenCompra;
import uy.mgcoders.dto.Producto;
import uy.mgcoders.dto.Resultado;
import uy.mgcoders.wsclient.pagosya.ConfirmacionPago;
import uy.mgcoders.wsclient.pagosya.ServicioRecepcionPagos;
import uy.mgcoders.wsclient.pagosya.ServicioRecepcionPagosService;
import uy.mgcoders.wsclient.stock.Reserva;
import uy.mgcoders.wsclient.stock.ResultadoAnulacion;
import uy.mgcoders.wsclient.stock.ServicioRecepcionStock;
import uy.mgcoders.wsclient.stock.ServicioRecepcionStockService;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pablo on 11/11/15.
 */
@Stateless
public class ComposicionESBHandler {

    private static final Logger logger = LoggerFactory.getLogger(ComposicionESBHandler.class);


    public Resultado procesarOrdenCompra(OrdenCompra ordenCompra) {

        Resultado resultado = new Resultado();

        uy.mgcoders.wsclient.stock.Resultado resultadoStockLocal = reservarProductosStockLocal(ordenCompra.getProductos());

        long idReserva;

        if(resultadoStockLocal.getCodigo().equalsIgnoreCase("OK")) {
            idReserva = resultadoStockLocal.getIdReserva();
        }
        else {
            idReserva = 2500; // TODO llamar a ePuerto
        }

        ConfirmacionPago confirmacionPago = pagarOrdenCompra(ordenCompra);

        if(confirmacionPago.getStatus().equalsIgnoreCase("true")) {
            resultado.setCodigo("OK");
        }
        else {
            resultado.setCodigo("Error");
            resultado.setDescripcion(confirmacionPago.getMessage());
            // Llamar al servicio publish suscribe para anular.
        }

        resultado.setIdCompra(ordenCompra.getIdOrden());

        return resultado;
    }


    private uy.mgcoders.wsclient.stock.Resultado reservarProductosStockLocal(List<Producto> productos) {
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
        return servicioRecepcionStock.reservarProducto(reserva);
    }


    private ConfirmacionPago pagarOrdenCompra(OrdenCompra ordenCompra) {
        double montoTotal = 0;
        for(Producto p : ordenCompra.getProductos()) {
            montoTotal += p.getCantidad() * p.getPrecioUnitario();
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        ServicioRecepcionPagos servicioRecepcionPagos = new ServicioRecepcionPagosService().getServicioRecepcionPagosPort();
        return servicioRecepcionPagos.recepcionPago(ordenCompra.getIdOrden(), String.valueOf(ordenCompra.getNumeroTarjeta()), String.valueOf(montoTotal), sdf.format(calendar.getTime()));
    }

    private ResultadoAnulacion anularReservaStockLocal(long idReserva) {
        ServicioRecepcionStock servicioRecepcionStock = new ServicioRecepcionStockService().getServicioRecepcionStockPort();
        return servicioRecepcionStock.anularReserva(idReserva);
    }
}
