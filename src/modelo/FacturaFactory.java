package modelo;

import modelo.decorators.PagoChequeDecorator;
import modelo.decorators.PagoEfectivoDecorator;
import modelo.decorators.PagoTarjetaCreditoDecorator;
import modelo.interfaces.IFactura;

import java.time.LocalDate;

/**
 * Factory para la clase factura
 */
public class FacturaFactory {
    /**
     * Genera una nueva factura y aplica patrón decorator al total
     *
     * @param concepto  El concepto de la factura
     * @param subtotal  El subtotal de la factura, sin descuentos aplicados
     * @param valorNeto EL valor total de la factura
     * @param medioDePago El medio de pago (EFECTIVO, CHEQUE, TARJETA)
     * @return La factura generada
     */
    public static IFactura getFactura(String concepto, double subtotal, double valorNeto, String medioDePago, LocalDate fecha) {
        IFactura respuesta = null;
        IFactura encapsulado = new Factura(concepto, subtotal, valorNeto, fecha);

        if (medioDePago.equalsIgnoreCase("EFECTIVO"))
            respuesta = new PagoEfectivoDecorator(encapsulado);
        else if (medioDePago.equalsIgnoreCase("CHEQUE"))
            respuesta = new PagoChequeDecorator(encapsulado);
        else if (medioDePago.equalsIgnoreCase("TARJETA"))
            respuesta = new PagoTarjetaCreditoDecorator(encapsulado);
        else
            respuesta = encapsulado;

        return respuesta;
    }

    public static IFactura getFactura(IFactura factura, String medioDePago) {
        IFactura respuesta = null;

        if (medioDePago.equalsIgnoreCase("EFECTIVO"))
            respuesta = new PagoEfectivoDecorator(factura);
        else if (medioDePago.equalsIgnoreCase("CHEQUE"))
            respuesta = new PagoChequeDecorator(factura);
        else if (medioDePago.equalsIgnoreCase("TARJETA"))
            respuesta = new PagoTarjetaCreditoDecorator(factura);
        else
            respuesta = factura;

        return respuesta;
    }
}
