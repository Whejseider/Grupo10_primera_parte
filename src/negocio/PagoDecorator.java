package negocio;

import negocio.interfaces.IAbonado;
import negocio.interfaces.IContrato;
import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

/**
 * Clase abstracta base para crear decoradores de pago. Este se puede utilizar
 * con cualquier clase que implemente
 * la interfaz IFactura
 */
public abstract class PagoDecorator implements IAbonado {
	private IAbonado abonado;

	/**
	 * Crea un nuevo decorator a partir de una clase que implemente IFactura.
	 * 
	 * @param abonado El facturable a decorar
	 */
	public PagoDecorator(IAbonado abonado) {
		assert abonado != null : "El facturable no puede ser nulo";

		this.abonado = abonado;
	}

	protected IAbonado getFacturable() {
		return this.abonado;
	}

	/**
	 * Delegacion del calculo de pago neto al facturable
	 */
	@Override
	public double getPagoNeto(IPromocion promo) {
		return this.getFacturable().getPagoNeto(promo);
	}

	/**
	 * Obtiene el precio total a pagar multiplicado por el modificador del
	 * decorador.
	 */
	public double getPagoMedioDePago(IPromocion promo) {
		return this.getPagoNeto(promo) * this.getModificador();
	}

	/**
	 * Delegacion de la obtención de detalle de pago al facturable
	 */
	@Override
	public IFactura generarFactura(IPromocion promo) {
		IFactura factura = this.getFacturable().generarFactura(promo);
		factura.setValorNeto(this.getPagoMedioDePago(promo));
		return factura;
	}

	@Override
	public void agregarFactura(IFactura factura) {
		this.getFacturable().agregarFactura(factura);
	}

	@Override
	public void facturar(IPromocion promo) {
		this.agregarFactura(this.generarFactura(promo));
	}

	@Override
	public String getDetalleFacturas() {
		return this.abonado.getDetalleFacturas();
	}

	@Override
	public IAbonado clone() throws CloneNotSupportedException {
		return (IAbonado) super.clone();
	}

	/**
	 * Obtiene el modificador de pago. Este se multiplicará por el precio neto del
	 * facturable.
	 */
	protected abstract double getModificador();

	@Override
	public void agregaContrato(IContrato contrato) {
		this.abonado.agregaContrato(contrato);
	}

	@Override
	public int cantidadDeContratos() {
		return this.abonado.cantidadDeContratos();
	}

	@Override
	public boolean tieneContrato(IContrato contrato) {
		return this.abonado.tieneContrato(contrato);
	}

	@Override
	public String getDetalle(IPromocion promo) {
		return this.abonado.getDetalle(promo);
	}
}
