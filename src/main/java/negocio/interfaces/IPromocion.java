package negocio.interfaces;

import negocio.contrato.ContratoComercio;
import negocio.contrato.ContratoVivienda;

public interface IPromocion {

    double getPrecioConDescuento(ContratoComercio contrato);

    double getPrecioConDescuento(ContratoVivienda contrato);

}