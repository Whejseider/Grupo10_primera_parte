package modelo;

import modelo.decorators.PagoChequeDecorator;
import modelo.decorators.PagoEfectivoDecorator;
import modelo.decorators.PagoTarjetaCreditoDecorator;
import modelo.interfaces.IFactura;

/**
 * Factory para la clase factura
 */
public class FacturaFactory {
    /**
     * Genera una nueva factura
     *
     * @param concepto  El concepto de la factura
     * @param subtotal  El subtotal de la factura, sin descuentos aplicados
     * @param valorNeto EL valor total de la factura
     * @param medioDePago El medio de pago (EFECTIVO, CHEQUE, TARJETA)
     * @return La factura generada
     */
    public static IFactura getFactura(String concepto, double subtotal, double valorNeto, String medioDePago) {
        IFactura respuesta = null;
        IFactura encapsulado = new Factura(concepto, subtotal, valorNeto);

        if (medioDePago.equalsIgnoreCase("EFECTIVO"))
            respuesta = new PagoEfectivoDecorator(encapsulado);
        else if (medioDePago.equalsIgnoreCase("CHEQUE"))
            respuesta = new PagoChequeDecorator(encapsulado);
        else if (medioDePago.equalsIgnoreCase("TARJETA"))
            respuesta = new PagoTarjetaCreditoDecorator(encapsulado);

        return respuesta;
    }
}
