package negocio;

import negocio.interfaces.IPromocion;
import negocio.interfaces.IServicioAlarma;

public class ContratoVivienda extends Contrato {
    private final double PRECIO_BASE = 8500;

    public ContratoVivienda(String domicilio, IServicioAlarma servicioAlarma) {
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
