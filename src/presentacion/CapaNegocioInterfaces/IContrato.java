package presentacion.CapaNegocio;

public interface IContrato {
    public void agregarCamara();

    public void agregarBotonAntipanico();

    public void contratarMovilAcompaniamiento();

    public double getPrecio(IPromocion promoActual);

    public double getPrecio();

    public String getDetalle();

}
