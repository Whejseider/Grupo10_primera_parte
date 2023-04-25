package modelo.contrato;

import modelo.alarma.IServicioAlarma;
import modelo.domicilio.Domicilio;

public interface IContrato extends Cloneable{
    double precioCamara = 3000;
    double precioBAP = 2000;
    double precioMovil = 7500;

    double getPrecio();

    int cantCamaras();

    void agregarCamaras(int cantidad);

    void eliminarCamaras(int cantidad);

    void agregarBAPs(int cantidad);

    void eliminarBAPs(int cantidad);

    void contratarMovil();

    void removerMovil();

    int cantBAPs();

    boolean isTieneMovil();

    IServicioAlarma getServicioAlarma();

    void setServicioAlarma(IServicioAlarma servicioAlarma);

    Domicilio getDomicilio();

    void setDomicilio(Domicilio domicilio);

    public Object clone() throws CloneNotSupportedException;
}
