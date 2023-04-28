package negocio.interfaces;

public interface IFactura {
	public String getDetalle();

	public String getConcepto();

	public double getSubtotal();

	public double getValorNeto();

	public void setValorNeto(double valorNeto);

}
