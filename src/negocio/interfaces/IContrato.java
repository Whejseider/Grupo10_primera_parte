package negocio.interfaces;

public interface IContrato extends Cloneable, IDetallable {
    public void agregarCamaras(int cantidad);

    public void agregarBtnesAntipanico(int cantidad);

    public void contratarMovilAcompaniamiento();

    public double getPrecio(IPromocion promoActual);

    public double getPrecio();

    public String getDomicilio();
    
    public IContrato clone() throws CloneNotSupportedException;
}
