package negocio.pago;

import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

public class PagoTarjetaCreditoDecorator extends PagoDecorator {
	private final double modificador = 1.05;

	public PagoTarjetaCreditoDecorator(IFactura facturable) {
		super(facturable);
	}

	@Override
	public double getPagoMedioDePago(IPromocion promo) {
		return this.getFacturable().getPagoNeto(promo) * modificador;
	}

}
