package negocio.decorators;

import negocio.interfaces.IAbonado;

/**
 * Decorador para pagos con cheque.
 */
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

		return (IAbonado) super.clone();
	}
}
