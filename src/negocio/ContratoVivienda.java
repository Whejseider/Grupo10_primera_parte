package negocio;

import negocio.interfaces.IPromocion;
import negocio.interfaces.IServicioAlarma;

public class ContratoVivienda extends Contrato {

    public ContratoVivienda(String domicilio, IServicioAlarma servicioAlarma) {
        super(domicilio, servicioAlarma);
    }

    private final double PRECIO_BASE = 8500;

    @Override
    protected double getPrecioBase() {
        return this.PRECIO_BASE;
    }

    @Override
    public double getPrecio(IPromocion promocionActual) {
        return promocionActual.getPrecioConDescuento(this);
    }

}
