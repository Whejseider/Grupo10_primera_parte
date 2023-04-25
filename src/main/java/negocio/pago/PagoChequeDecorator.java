package negocio.pago;

import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

public class PagoChequeDecorator extends PagoDecorator {
	private final double modificador = 1.1;

	public PagoChequeDecorator(IFactura facturable) {
		super(facturable);
	}

	@Override
	public double getPagoMedioDePago(IPromocion promo) {
		return this.getFacturable().getPagoNeto(promo) * modificador;
	}

}
