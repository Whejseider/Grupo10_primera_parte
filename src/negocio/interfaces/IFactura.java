package negocio.interfaces;

public interface IFactura extends Cloneable {
	public String getDetalle();

	public double getPagoNeto();

	public double getPagoMedioDePago();

}
