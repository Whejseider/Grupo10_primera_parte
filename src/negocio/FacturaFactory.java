package negocio;

import negocio.interfaces.IFactura;

/**
 * Factory para la clase factura
 */
public class FacturaFactory {
    /**
     * Genera una nueva factura
     * @param concepto El concepto de la factura
     * @param subtotal El subtotal de la factura, sin descuentos aplicados
     * @param valorNeto EL valor total de la factura
     * @return La factura generada
     */
    public static IFactura getFactura(String concepto, double subtotal, double valorNeto) {
        return new Factura(concepto, subtotal, valorNeto);
    }
}
