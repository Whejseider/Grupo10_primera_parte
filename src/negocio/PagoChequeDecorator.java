package negocio;

import negocio.interfaces.IAbonado;

public class PagoChequeDecorator extends PagoDecorator {
	private final double modificador = 1.1;

	public PagoChequeDecorator(IAbonado facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}

	@Override
	public IAbonado clone() throws CloneNotSupportedException {
		return (IAbonado) new PagoChequeDecorator(this.getFacturable().clone());
	}
}
