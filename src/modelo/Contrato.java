package modelo;

import modelo.interfaces.IContrato;
import modelo.interfaces.IPromocion;
import modelo.interfaces.IServicioAlarma;

import java.io.Serializable;

/**
 * Clase abstracta que representa un contrato de alarma.
 */
public abstract class Contrato implements IContrato, Serializable {
    private static int numero = 0;
    private final int ID;
    private final String domicilio;
    private IServicioAlarma servicioAlarma;

    /**
     * Constructor de la clase Contrato.
     * 
     * <b>pre: </b>: El domilicio debe estar definido y no ser vacío. El objeto
     * servicio no puede ser nulo.
     * 
     * @param domicilio      El domicilio donde quedará registrado el contrato. Este
     *                       se usa para verificar igualdad entre contratos.
     * @param servicioAlarma Los servicios adicionales a adjuntar al contrato.
     */
    public Contrato(String domicilio, IServicioAlarma servicioAlarma) {
        assert (domicilio != null);
        assert (servicioAlarma != null);
        this.ID = numero++;
        this.servicioAlarma = servicioAlarma;
        this.domicilio = domicilio;
    }

    /**
     * Devuelve el domicilio vinculado al contrato
     * 
     * @return El domicilio del contrato
     */
    public String getDomicilio() {
        return this.domicilio;
    }

    /**
     * Agrega camaras al contrato
     * 
     * <b>pre:</b> La cantidad no puede ser menor a cero.
     * 
     * @param cantidad La cantidad de camaras a agregar
     */
    public void agregarCamaras(int cantidad) {
        assert (cantidad >= 0);
        this.servicioAlarma.agregarCamaras(cantidad);
    }

    /**
     * Agrega botones antipánico al contrato.
     * 
     * <b>pre:</b> La cantidad no puede ser menor a cero.
     * 
     * @param cantidad La cantidad de botones a agregar
     */
    public void agregarBtnesAntipanico(int cantidad) {
        assert (cantidad >= 0);
        this.servicioAlarma.agregarBotonesAntipanico(cantidad);
    }

    /**
     * Contrata movil de acompañamiento al contrato
     */
    public void contratarMovilAcompaniamiento() {
        this.servicioAlarma.contratarMovil();
    }

    /**
     * Obtiene el precio base del contrato. Varía dependiendo del tipo de contrato.
     */
    protected abstract double getPrecioBase();

    /**
     * Obtiene el precio total del contrato, sin promociones aplicadas.
     */
    public double getPrecio() {
        return this.servicioAlarma.getPrecio() + getPrecioBase();
    }

    /**
     * Obtiene el ID o identificador del contrato
     * @return El identificador del contrato
     */
    public int getIdentificador() {
        return this.ID;
    }

    /**
     * Genera una string con el detalle del contrato, con su total y contrataciones.
     */
    public String getDetalle(IPromocion promo) {
        StringBuilder detalle = new StringBuilder();

        detalle.append("Contrato N°" + this.ID + "\n");
        detalle.append(this.servicioAlarma.getDetalle());
        detalle.append("Costo $" + this.getPrecio(promo) + "\n");

        return detalle.toString();
    }

    /**
     * Devuelve verdadero si dos contratos se consideran iguales. Son iguales si
     * coincide su domicilio.<br>
     * 
     * @param obj El objeto a comparar.
     * @return Verdadero si son iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Contrato)) {
            return false;
        }

        Contrato contrato = (Contrato) obj;

        // No es necesario fijarse si el ID es igual porque es autoincremental.
        return this.domicilio.equals(contrato.getDomicilio());
    }

    /**
     * Clona el contrato
     */
    @Override
    public IContrato clone() throws CloneNotSupportedException {
        Contrato contratoCloneado = (Contrato) super.clone();

        contratoCloneado.servicioAlarma = (ServicioAlarma) this.servicioAlarma.clone();

        return contratoCloneado;
    }

    @Override
    public boolean getTieneMovil() {
        return this.servicioAlarma.getTieneMovil();
    }

    @Override
    public int getCantCamaras() {
        return this.servicioAlarma.getCantCamaras();
    }

    @Override
    public int getCantBotones() {
        return this.servicioAlarma.getCantBotonesAntipanico();
    }

    /**
     * Obtiene el precio con descuento por promoción del contrato.
     * 
     * <b>pre:</b> La promocion debe estar definida
     * 
     * @param promocionActual La promo a aplicar.
     */
    public abstract double getPrecio(IPromocion promocionActual);

    public String toString() {
        return "contrato domicilio: " + this.domicilio + this.servicioAlarma.toString();
    }
}
