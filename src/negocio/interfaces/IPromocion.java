package negocio.interfaces;

import negocio.ContratoComercio;
import negocio.ContratoVivienda;

public interface IPromocion {

	/**
	 * Obtiene el precio de un contrato comercio aplicando double-dispatch.
	 * @param contrato El contrato comercio con el que calcular el precio.
	 * @return El valor total con descuento
	 */
    double getPrecioConDescuento(ContratoComercio contrato);

    /**
	 * Obtiene el precio de un contrato vivienda aplicando double-dispatch.
	 * @param contrato El contrato vivienda con el que calcular el precio.
	 * @return El valor total con descuento
	 */
    double getPrecioConDescuento(ContratoVivienda contrato);

}