package modelo.decorators;

import modelo.interfaces.IAbonado;

/**
 * Decorador para pagos en efectivo.
 */
public class PagoEfectivoDecorator extends PagoDecorator {
	private final double modificador = 0.8;

	public PagoEfectivoDecorator(IAbonado facturable) {
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
