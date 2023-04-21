package negocio.interfaces;

public interface IPromocion {

    double getPrecioConDescuento(ContratoComercio contrato);

    double getPrecioConDescuento(ContratoVivienda contrato);

}
