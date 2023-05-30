package modelo.interfaces;

public interface IFactura extends Cloneable {
	public String getDetalle();

	public String getConcepto();

	public double getSubtotal();

	public double getValorNeto();

	public void setValorNeto(double valorNeto);

	public IFactura clone() throws CloneNotSupportedException;

}
