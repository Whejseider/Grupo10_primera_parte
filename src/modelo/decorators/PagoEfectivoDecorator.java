package modelo.decorators;

import modelo.interfaces.IAbonado;
import modelo.interfaces.IFactura;

import java.text.DecimalFormat;

/**
 * Decorador para pagos en efectivo.
 */
public class PagoEfectivoDecorator extends PagoDecorator {
	private final double modificador = 0.8;

	public PagoEfectivoDecorator(IFactura factura) {
		super(factura);
	}
	@Override
	public String getDetalle() {
		DecimalFormat numberFormat = new DecimalFormat("#.##");
		return this.getFactura().getDetalle() + "\n TOTAL: $" + numberFormat.format(this.getValorNeto()) + "\n";
	}

	@Override
	public double getValorNeto() {
		return this.getFactura().getValorNeto() * modificador;
	}

	@Override
	public IFactura clone() throws CloneNotSupportedException {

		return (IFactura) super.clone();
	}
}
