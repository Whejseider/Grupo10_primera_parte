package negocio;

import negocio.interfaces.IContrato;
import negocio.interfaces.IPromocion;
import negocio.interfaces.IServicioAlarma;

public abstract class Contrato implements IContrato {
    private static int numero = 0;
    private static int ID;
    private String domicilio;
    private IServicioAlarma servicioAlarma;

    public Contrato(String domicilio, IServicioAlarma servicioAlarma) {
        assert (domicilio != null);
        assert (servicioAlarma != null);
        ID = numero++;
        this.servicioAlarma = servicioAlarma;
    }

    public void agregarCamaras(int cantidad) {
        assert (cantidad >= 0);
        this.servicioAlarma.agregarCamaras(cantidad);
    }

    public void agregarBtnesAntipanico(int cantidad) {
        assert (cantidad >= 0);
        this.servicioAlarma.agregarBtnesAntipanico(cantidad);
    }

    public void contratarMovilAcompaniamiento() {
        this.servicioAlarma.contratarMovilAcompaniamiento();
    }

    protected abstract double getPrecioBase();

    public double getPrecio() {
        return this.servicioAlarma.getPrecio() + getPrecioBase();
    }

    public abstract double getPrecio(IPromocion promocionActual);

}
