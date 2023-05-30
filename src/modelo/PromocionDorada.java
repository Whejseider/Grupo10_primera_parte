package modelo;

/**
 * Subclase de Promocion para promos doradas.
 */
public class PromocionDorada extends Promocion {

    public PromocionDorada() {
        this.descuentoComercio = 2500;
        this.descuentoVivienda = 1500;
        this.nominal = true;
    }

    @Override
    public double getPrecioConDescuento(ContratoComercio contrato) {
        assert (contrato != null);
        if (contrato.getPrecio() < descuentoComercio)
            return 0;
        return contrato.getPrecio() - descuentoComercio;
    }

    @Override
    public double getPrecioConDescuento(ContratoVivienda contrato) {
        assert (contrato != null);
        if (contrato.getPrecio() < descuentoVivienda)
            return 0;
        return contrato.getPrecio() - descuentoVivienda;
    }

}
