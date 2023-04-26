package negocio.pago;

import negocio.interfaces.IFactura;

public class PagoTarjetaCreditoDecorator extends PagoDecorator {
	private final double modificador = 1.05;

	public PagoTarjetaCreditoDecorator(IFactura facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}
	
	@Override
	public IFactura clone() throws CloneNotSupportedException {
		return (IFactura) new PagoTarjetaCreditoDecorator(this.getFacturable().clone());
	}

}
