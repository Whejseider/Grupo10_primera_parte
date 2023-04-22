package negocio;

import negocio.interfaces.IContrato;
import negocio.interfaces.IPromocion;
import negocio.interfaces.IServicioAlarma;

public abstract class Contrato implements IContrato {
    private static int numero = 0;
    private int ID;
    private String domicilio;
    private IServicioAlarma servicioAlarma;

    public Contrato(String domicilio, IServicioAlarma servicioAlarma) {
        assert (domicilio != null);
        assert (servicioAlarma != null);
        this.ID = numero++;
        this.servicioAlarma = servicioAlarma;
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return this.domicilio;
    }

    public void agregarCamaras(int cantidad) {
        assert (cantidad >= 0);
        this.servicioAlarma.agregarCamaras(cantidad);
    }

    public void agregarBtnesAntipanico(int cantidad) {
        assert (cantidad >= 0);
        this.servicioAlarma.agregarBotonesAntipanico(cantidad);
    }

    public void contratarMovilAcompaniamiento() {
        this.servicioAlarma.contratarMovil();
    }

    protected abstract double getPrecioBase();

    public double getPrecio() {
        return this.servicioAlarma.getPrecio() + getPrecioBase();
    }

    public abstract double getPrecio(IPromocion promocionActual);

}
