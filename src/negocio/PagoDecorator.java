package negocio;

import java.util.ArrayList;

import negocio.interfaces.IFacturable;
import negocio.interfaces.IPromocion;

/**
 * Clase abstracta base para crear decoradores de pago. Este se puede utilizar con cualquier clase que implemente
 * la interfaz IFactura
 */
public abstract class PagoDecorator implements IFacturable {
	private IFacturable facturable;
	/**
	 * Crea un nuevo decorator a partir de una clase que implemente IFactura.
	 * @param facturable El facturable a decorar
	 */
	public PagoDecorator(IFacturable facturable) {
		assert facturable != null : "El facturable no puede ser nulo";
		
		this.facturable = facturable;
	}
	
	protected IFacturable getFacturable() {
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
	public Factura generarFactura(IPromocion promo) {
		Factura factura = this.getFacturable().generarFactura(promo);
		factura.setValorNeto(this.getPagoMedioDePago(promo));
		return factura;
	}
	
	@Override
	public void agregarFactura(Factura factura) {
		this.getFacturable().agregarFactura(factura);
	}
	
	@Override
	public void facturar(IPromocion promo) {
		this.agregarFactura(this.generarFactura(promo));
	}
	
	@Override
	public String getDetalleFacturas() {
		return this.facturable.getDetalleFacturas();
	}
	
	@Override
	public IFacturable clone() throws CloneNotSupportedException {
		return (IFacturable) super.clone();
	}
	
	/**
	 * Obtiene el modificador de pago. Este se multiplicará por el precio neto del facturable.
	 */
	protected abstract double getModificador();
}
