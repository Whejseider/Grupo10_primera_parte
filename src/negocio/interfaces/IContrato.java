package negocio.interfaces;

public interface IContrato {
    public void agregarCamaras(int cantidad);

    public void agregarBtnesAntipanico(int cantidad);

    public void contratarMovilAcompaniamiento();

    public double getPrecio(IPromocion promoActual);

    public double getPrecio();

    public String getDetalle();

}
