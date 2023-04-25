package negocio.pago;

import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

public abstract class PagoDecorator implements IFactura {
	private IFactura facturable;
	
	/**
	 * Crea un nuevo decorator a partir de una clase que implemente IFacturable.
	 * @param facturable El facturable a decorar
	 */
	public PagoDecorator(IFactura facturable) {
		assert facturable != null : "El facturable no puede ser nulo";
		this.facturable = facturable;
	}
	
	protected IFactura getFacturable() {
		return this.facturable;
	}
	
	@Override 
	public double getPagoNeto(IPromocion promo) {
		return this.getFacturable().getPagoNeto(promo);
	}

	@Override
	public String getDetalle() {
		return this.getFacturable().getDetalle();
	}
}
