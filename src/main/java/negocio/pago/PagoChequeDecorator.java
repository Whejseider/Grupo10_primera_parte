package negocio.pago;

import negocio.interfaces.IFactura;

public class PagoChequeDecorator extends PagoDecorator {
	private final double modificador = 1.1;

	public PagoChequeDecorator(IFactura facturable) {
		super(facturable);
	}

	@Override
	public double getModificador() {
		return modificador;
	}
	
	@Override
	public IFactura clone() throws CloneNotSupportedException {
		return (IFactura) new PagoChequeDecorator(this.getFacturable().clone());
	}
}
