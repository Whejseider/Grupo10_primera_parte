package presentacion.CapaNegocioInterfaces;

public interface IServicioAlarma {
    public double getPrecio();

    public void agregarCamaras(int cantidad);

    public void agregarBtnesAntipanico(int cantidad);

    public void contratarMovilAcompaniamiento();

    public String getDetalle();
}
