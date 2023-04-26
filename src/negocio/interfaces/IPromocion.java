package negocio.interfaces;

import negocio.ContratoComercio;
import negocio.ContratoVivienda;

public interface IPromocion {

    double getPrecioConDescuento(ContratoComercio contrato);

    double getPrecioConDescuento(ContratoVivienda contrato);

}