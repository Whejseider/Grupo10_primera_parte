package negocio.interfaces;

public interface IFactura extends Cloneable {
	public String getDetalle();

	/**
	 * Obtiene el pago neto previo a cualquier descuento sobre el total.
	 * @param promo La promoción a aplicar
	 * @return El precio total.
	 */
	public double getPagoNeto(IPromocion promo);

	/**
	 * Obtiene el pago total calculado con el medio de pago
	 * @param promo La promoción a aplicar
	 * @return El precio total descontado.
	 */
	public double getPagoMedioDePago(IPromocion promo);

	public IFactura clone() throws CloneNotSupportedException;
}
