package negocio.pago;

import negocio.interfaces.IFactura;

public class PagoEfectivoDecorator extends PagoDecorator {
	private final double modificador = 0.8;
	
	public PagoEfectivoDecorator(IFactura facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}
	
	@Override
	public IFactura clone() throws CloneNotSupportedException {
		return (IFactura) new PagoEfectivoDecorator(this.getFacturable().clone());
	}
}
