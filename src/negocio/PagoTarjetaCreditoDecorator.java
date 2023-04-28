package negocio;

import negocio.interfaces.IAbonado;

public class PagoTarjetaCreditoDecorator extends PagoDecorator {
	private final double modificador = 1.05;

	public PagoTarjetaCreditoDecorator(IAbonado facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}

	@Override
	public IAbonado clone() throws CloneNotSupportedException {
		return (IAbonado) new PagoTarjetaCreditoDecorator(this.getFacturable().clone());
	}

}
