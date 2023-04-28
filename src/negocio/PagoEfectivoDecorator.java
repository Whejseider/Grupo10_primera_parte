package negocio;

import negocio.interfaces.IFacturable;

public class PagoEfectivoDecorator extends PagoDecorator {
	private final double modificador = 0.8;
	
	public PagoEfectivoDecorator(IFacturable facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}
	
	@Override
	public IFacturable clone() throws CloneNotSupportedException {
		return (IFacturable) new PagoEfectivoDecorator(this.getFacturable().clone());
	}
}
