package negocio;

import negocio.interfaces.IFacturable;

public class PagoChequeDecorator extends PagoDecorator {
	private final double modificador = 1.1;

	public PagoChequeDecorator(IFacturable facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}
	
	@Override
	public IFacturable clone() throws CloneNotSupportedException {
		return (IFacturable) new PagoChequeDecorator(this.getFacturable().clone());
	}
}
