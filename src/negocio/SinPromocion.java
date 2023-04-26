package negocio;

public class SinPromocion extends Promocion {

    public SinPromocion() {
        this.nominal = true;
        this.descuentoComercio = 0;
        this.descuentoComercio = 0;
    }

    @Override
    public double getPrecioConDescuento(ContratoComercio contrato) {
        assert (contrato != null);
        return contrato.getPrecio();
    }

    @Override
    public double getPrecioConDescuento(ContratoVivienda contrato) {
        assert (contrato != null);
        return contrato.getPrecio();
    }

}