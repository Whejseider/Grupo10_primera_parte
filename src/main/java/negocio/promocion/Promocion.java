package negocio.promocion;

import negocio.interfaces.IPromocion;

public abstract class Promocion implements IPromocion {
    protected double descuentoComercio;
    protected double descuentoVivienda;
    protected boolean nominal;// true nominal false porcentual

    public Promocion() {
    }

    public boolean esNominal() {
        return this.nominal;
    }

    public boolean esPorcentual() {
        return !this.nominal;
    }

}
