package negocio.interfaces;

public interface IFactura extends Cloneable {
	public String getDetalle();

	public double getPagoNeto(IPromocion promo);

	public double getPagoMedioDePago(IPromocion promo);

	public IFactura clone() throws CloneNotSupportedException;
}
