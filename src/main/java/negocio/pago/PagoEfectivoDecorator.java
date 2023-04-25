package negocio.pago;

import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

public class PagoEfectivoDecorator extends PagoDecorator {
	private final double modificador = 0.8;
	
	public PagoEfectivoDecorator(IFactura facturable) {
		super(facturable);
	}

	@Override
	public double getPagoMedioDePago(IPromocion promo) {
		return this.getFacturable().getPagoNeto(promo) * modificador;
	}
}
