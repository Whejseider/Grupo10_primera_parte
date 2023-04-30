package negocio.interfaces;

public interface IFactura extends Cloneable {
	public String getDetalle();

	public String getConcepto();

	public IFactura clone() throws CloneNotSupportedException;

	public double getSubtotal();

	public double getValorNeto();

	public void setValorNeto(double valorNeto);

}
