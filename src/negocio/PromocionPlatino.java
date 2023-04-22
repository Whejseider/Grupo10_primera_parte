package negocio;

public class PromocionPlatino extends Promocion {

    public PromocionPlatino() {
        super();
        this.descuentoComercio = 0.3;
        this.descuentoVivienda = 0.35;
        this.nominal = false;
    }

    @Override
    public double getPrecioConDescuento(ContratoComercio contrato) {
        assert (contrato != null);
        return contrato.getPrecio() * (1 - this.descuentoComercio);
    }

    @Override
    public double getPrecioConDescuento(ContratoVivienda contrato) {
        assert (contrato != null);
        return contrato.getPrecio() * (1 - this.descuentoVivienda);
    }

}
