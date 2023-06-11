package modelo;

import modelo.interfaces.IPromocion;

/**
 * Clase abstracta para las promociones para contratos. Permite crear
 * subclases que implementen sus propios descuentos.
 */
public abstract class Promocion implements IPromocion {
    protected double descuentoComercio;
    protected double descuentoVivienda;
    protected boolean nominal;// true nominal false porcentual

    public Promocion() {
    }

    public double getDescuentoComercio() {
        return descuentoComercio;
    }

    public void setDescuentoComercio(double descuentoComercio) {
        this.descuentoComercio = descuentoComercio;
    }

    public double getDescuentoVivienda() {
        return descuentoVivienda;
    }

    public void setDescuentoVivienda(double descuentoVivienda) {
        this.descuentoVivienda = descuentoVivienda;
    }

    public boolean isNominal() {
        return nominal;
    }

    public void setNominal(boolean nominal) {
        this.nominal = nominal;
    }

    /**
     * @return Verdadero si el tipo de descuento de la promoción es nominal.
     */
    public boolean esNominal() {
        return this.nominal;
    }

    /**
     * @return Verdadero si el tipo de descuento de la promoción es porcentual.
     */
    public boolean esPorcentual() {
        return !this.nominal;
    }

}
