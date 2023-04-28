package negocio.interfaces;

import negocio.Factura;

public interface IFacturable extends Cloneable {

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
	
	public Factura generarFactura(IPromocion promo);
	
	public void facturar(IPromocion promo);
	
	public void agregarFactura(Factura factura);
	
	public String getDetalleFacturas();

	public IFacturable clone() throws CloneNotSupportedException;
}
