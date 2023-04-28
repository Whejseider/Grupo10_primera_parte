package negocio;

import negocio.interfaces.IFacturable;

public class PagoTarjetaCreditoDecorator extends PagoDecorator {
	private final double modificador = 1.05;

	public PagoTarjetaCreditoDecorator(IFacturable facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}
	
	@Override
	public IFacturable clone() throws CloneNotSupportedException {
		return (IFacturable) new PagoTarjetaCreditoDecorator(this.getFacturable().clone());
	}

}
