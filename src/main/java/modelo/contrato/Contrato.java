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

    /**
     * Genera un contrato dado un Servicio y un Domicilio con el precio base del servicio
     * @param servicioAlarma Tipo del servicio de alarma Vivienda o Comercio
     * @param domicilio Domicilio al cual se le hara el contrato con el servicio
     */
    public Contrato(IServicioAlarma servicioAlarma, Domicilio domicilio) {
        assert servicioAlarma != null : "Se debe ingresar un servicio!";
        assert domicilio != null : "Se debe ingresar un domicilio!";

        this.servicioAlarma = servicioAlarma;
        this.domicilio = domicilio;
        this.ID = _ID++;
        this.precio = servicioAlarma.getPrecio();
        this.cantCamaras = 0;
        this.cantBAPs = 0;
        this.tieneMovil = false;
    }

    /**
     * Devuelve el precio de un contrato sin aplicar descuentos ni promociones<br>
     * donde cantBAPs es cantidad de Botones AntiPanico
     * PRE: precio >= 0, cantCamaras >= 0, cantBAPs >= 0
     * POST: Precio del contrato >= 0
     * @return precio del contrato
     */
    @Override
    public double getPrecio() {
        assert this.precio >= 0;
        assert cantCamaras >= 0;
        assert cantBAPs >= 0;

        return this.precio +
                (cantCamaras * precioCamara) +
                (cantBAPs * precioBAP) +
                (isTieneMovil() ? precioMovil : 0);
    }

    public void setPrecio(double precio) {
        assert precio >= 0: "El precio no puede ser una cantidad negativa!";
        this.precio = precio;
    }

    @Override
    public int cantCamaras() {
        return this.cantCamaras;
    }

    @Override
    public void agregarCamaras(int cantidad) {
        assert cantidad >= 0: "La cantidad no puede ser una cantidad negativa!";
        this.cantCamaras += cantidad;
    }

    @Override
    public void eliminarCamaras(int cantidad) {
        assert cantidad >= 0: "La cantidad no puede ser una cantidad negativa!";
        this.cantCamaras -= cantidad;
    }

    @Override
    public void agregarBAPs(int cantidad) {
        assert cantidad >= 0: "La cantidad no puede ser una cantidad negativa!";
        this.cantBAPs += cantidad;
    }

    @Override
    public void eliminarBAPs(int cantidad) {
        assert cantidad >= 0: "La cantidad no puede ser una cantidad negativa!";
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
        assert servicioAlarma != null: "El servicio de alarma debe existir!";
        this.servicioAlarma = servicioAlarma;
    }

    @Override
    public Domicilio getDomicilio() {
        return domicilio;
    }

    @Override
    public void setDomicilio(Domicilio domicilio) {
        assert domicilio != null: "El domicilio debe existir!";
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
