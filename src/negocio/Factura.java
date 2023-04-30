package negocio;

import negocio.interfaces.IFactura;

public class Factura implements IFactura {
	private final String concepto;
	private final double subtotal;
	private double valorNeto;
	private final int id;
	private static int numero = 1;

	public Factura(String concepto, double subtotal, double valorNeto) {
		this.valorNeto = valorNeto;
		this.concepto = concepto;
		this.subtotal = subtotal;
		this.id = numero++;
	}

	public String getConcepto() {
		return concepto;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public double getValorNeto() {
		return valorNeto;
	}

	public void setValorNeto(double valorNeto) {
		this.valorNeto = valorNeto;
	}

	public IFactura clone() throws CloneNotSupportedException {
		return (IFactura) super.clone();
	}

	public String getDetalle() {
		return "FACTURA N°" + this.id + "\n" + this.concepto + "SUBTOTAL: $" + Double.toString(subtotal) + "\nTOTAL: $"
				+ Double.toString(valorNeto) + "\n";
	}
}
