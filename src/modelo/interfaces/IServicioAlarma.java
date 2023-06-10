package modelo.interfaces;

import java.io.Serializable;

public interface IServicioAlarma extends Cloneable, Serializable {
    public void agregarCamaras(int cantidad);

    public void setCamaras(int cantidad);

    public void agregarBotonesAntipanico(int cantidad);

    public void setBotonesAntipanico(int cantidad);

    public int getCantCamaras();

    public int getCantBotonesAntipanico();

    public boolean getTieneMovil();

    public void contratarMovil();

    public double getPrecio();

    public void quitarMovil();

    public IServicioAlarma clone() throws CloneNotSupportedException;

    public String getDetalle();

}
