package negocio.interfaces;

public interface IContrato extends Cloneable {
    public void agregarCamaras(int cantidad);

    public void agregarBtnesAntipanico(int cantidad);

    public void contratarMovilAcompaniamiento();

    public double getPrecio(IPromocion promoActual);

    public double getPrecio();

    public String getDomicilio();

    Object clone();
}
