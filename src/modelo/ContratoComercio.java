package modelo;

import modelo.interfaces.IPromocion;
import modelo.interfaces.IServicioAlarma;

/**
 * Subclase para contratos de comercio.
 */
public class ContratoComercio extends Contrato {
    private final double PRECIO_BASE = 10000;

    public ContratoComercio(String domicilio, IServicioAlarma servicioAlarma) {
        super(domicilio, servicioAlarma);
    }

    @Override
    protected double getPrecioBase() {
        return this.PRECIO_BASE;
    }

    @Override
    public double getPrecio(IPromocion promocionActual) {
        return promocionActual.getPrecioConDescuento(this);
    }

}
