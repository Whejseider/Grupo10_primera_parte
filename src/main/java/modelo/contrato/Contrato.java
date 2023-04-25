package modelo.contrato;

import modelo.alarma.IServicioAlarma;
import modelo.alarma.ServicioAlarma;
import modelo.domicilio.Domicilio;

public class Contrato implements IContrato {
    private static int _ID = 0;
    private final int ID;
    private IServicioAlarma servicioAlarma;
    private Domicilio domicilio;
    private double precio;
    private int cantCamaras;
    private int cantBAPs;
    private boolean tieneMovil;

    public Contrato(IServicioAlarma servicioAlarma, Domicilio domicilio) {
        this.servicioAlarma = servicioAlarma;
        this.domicilio = domicilio;
        this.ID = _ID++;
        this.precio = servicioAlarma.getPrecio();
        this.cantCamaras = 0;
        this.cantBAPs = 0;
        this.tieneMovil = false;
    }

    @Override
    public double getPrecio() {
        return this.precio +
                (cantCamaras * precioCamara) +
                (cantBAPs * precioBAP) +
                (isTieneMovil() ? precioMovil : 0);
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int cantCamaras() {
        return this.cantCamaras;
    }

    @Override
    public void agregarCamaras(int cantidad) {
        this.cantCamaras += cantidad;
    }

    @Override
    public void eliminarCamaras(int cantidad) {
        this.cantCamaras -= cantidad;
    }

    @Override
    public void agregarBAPs(int cantidad) {
        this.cantBAPs += cantidad;
    }

    @Override
    public void eliminarBAPs(int cantidad) {
        this.cantBAPs -= cantidad;
    }

    @Override
    public void contratarMovil() {
        this.tieneMovil = true;
    }

    @Override
    public void removerMovil() {
        this.tieneMovil = false;
    }

    @Override
    public int cantBAPs() {
        return this.cantBAPs;
    }

    @Override
    public boolean isTieneMovil() {
        return this.tieneMovil;
    }

    public int getID() {
        return ID;
    }

    @Override
    public IServicioAlarma getServicioAlarma() {
        return servicioAlarma;
    }

    @Override
    public void setServicioAlarma(IServicioAlarma servicioAlarma) {
        this.servicioAlarma = servicioAlarma;
    }

    @Override
    public Domicilio getDomicilio() {
        return domicilio;
    }

    @Override
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Contrato contratoClon = null;

        try {
            contratoClon = (Contrato) super.clone();
            contratoClon.servicioAlarma = (IServicioAlarma) this.servicioAlarma.clone();
            contratoClon.domicilio = (Domicilio) this.domicilio.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return contratoClon;
    }
}
