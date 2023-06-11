package modelo.interfaces;

import java.io.Serializable;

import modelo.ContratoComercio;
import modelo.ContratoVivienda;

public interface IPromocion extends Serializable {

	/**
	 * Obtiene el precio de un contrato comercio aplicando double-dispatch.
	 * 
	 * @param contrato El contrato comercio con el que calcular el precio.
	 * @return El valor total con descuento
	 */
	double getPrecioConDescuento(ContratoComercio contrato);

	/**
	 * Obtiene el precio de un contrato vivienda aplicando double-dispatch.
	 * 
	 * @param contrato El contrato vivienda con el que calcular el precio.
	 * @return El valor total con descuento
	 */
	double getPrecioConDescuento(ContratoVivienda contrato);

}