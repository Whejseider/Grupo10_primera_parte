package negocio.interfaces;

public interface IServicioAlarma {
    public double getPrecio();

    public void agregarCamaras(int cantidad);

    public void setCamaras(int cantidad);

    public void agregarBotonesAntipanico(int cantidad);

    public void setBotonesAntipanico(int cantidad);

    public void contratarMovil();

    public void quitarMovil();

}
