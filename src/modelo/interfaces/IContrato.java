package modelo.interfaces;

import java.io.Serializable;

public interface IContrato extends Cloneable, IDetallable, Serializable {
    public void agregarCamaras(int cantidad);

    public void agregarBtnesAntipanico(int cantidad);

    public void contratarMovilAcompaniamiento();

    public double getPrecio(IPromocion promoActual);

    public double getPrecio();

    public String getDomicilio();

    public int getIdentificador();

    public int getCantCamaras();

    public int getCantBotones();

    public boolean getTieneMovil();

    public IContrato clone() throws CloneNotSupportedException;
}
