package negocio;

import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

/**
 * Clase abstracta base para crear decoradores de pago. Este se puede utilizar con cualquier clase que implemente
 * la interfaz IFactura
 */
public abstract class PagoDecorator implements IFactura {
	private IFactura facturable;
	/**
	 * Crea un nuevo decorator a partir de una clase que implemente IFactura.
	 * @param facturable El facturable a decorar
	 */
	public PagoDecorator(IFactura facturable) {
		assert facturable != null : "El facturable no puede ser nulo";
		
		this.facturable = facturable;
	}
	
	protected IFactura getFacturable() {
		return this.facturable;
	}
	
	/**
	 * Delegacion del calculo de pago neto al facturable
	 */
	@Override 
	public double getPagoNeto(IPromocion promo) {
		return this.getFacturable().getPagoNeto(promo);
	}
	
	/**
	 * Obtiene el precio total a pagar multiplicado por el modificador del decorador.
	 */
	public double getPagoMedioDePago(IPromocion promo) {
		return this.getPagoNeto(promo) * this.getModificador();
	}
	
	/**
	 * Delegacion de la obtención de detalle de pago al facturable
	 */
	@Override
	public String getDetalle() {
		return this.getFacturable().getDetalle();
	}
	
	@Override
	public IFactura clone() throws CloneNotSupportedException {
		return (IFactura) super.clone();
	}
	
	/**
	 * Obtiene el modificador de pago. Este se multiplicará por el precio neto del facturable.
	 */
	protected abstract double getModificador();
}
