package modelo.decorators;

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

	/**
	 * Obtiene los detalles de la factura y el valor neto con el descuento aplicado.
	 * Se usa un formato para los numeros flotantes
	 * @return los detalles de la factura y el valor neto con el descuento aplicado
	 */
	@Override
	public String getDetalle() {
		DecimalFormat numberFormat = new DecimalFormat("#.##");
		return this.getFactura().getDetalle() + "\n TOTAL: $" + numberFormat.format(this.getValorNeto()) + "\n";
	}

	/**
	 * Obtiene el valor neto aplicado con el descuento en efectivo
	 * @return el valor neto con descuento en efectivo
	 */
	@Override
	public double getValorNeto() {
		return this.getFactura().getValorNeto() * modificador;
	}

	@Override
	public IFactura clone() throws CloneNotSupportedException {

		return (IFactura) super.clone();
	}
}
