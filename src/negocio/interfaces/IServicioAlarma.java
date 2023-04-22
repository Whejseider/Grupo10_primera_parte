package negocio.interfaces;

public interface IServicioAlarma {
    public void agregarCamaras(int cantidad);
    
    public void setCamaras(int cantidad);

    public void agregarBotonesAntipanico(int cantidad);

    public void setBotonesAntipanico(int cantidad);

    public int getCantCamaras();

    public int getCantBotonesAntipanico();

    public boolean getTieneMovil();
    
    public void contratarMovil();
    
    public void quitarMovil();
}
