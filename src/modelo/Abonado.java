package modelo;

import modelo.excepciones.ServicioEnCursoException;
import modelo.excepciones.SinContratosException;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import modelo.tecnicos.ServicioTecnico;
import modelo.tecnicos.Tecnico;
import modelo.interfaces.*;

import java.time.LocalDate;
import javax.swing.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase abstracta que representa un cliente.
 */
public abstract class Abonado implements IAbonado {
    private String nombre;
    private String dni;
    private Thread threadServicio = null;
    private ServicioTecnico service = null;
    private ArrayList<IContrato> contratos;
    private ArrayList<IFactura> facturas;

    public Abonado() {
    }

    /**
     * Constructor de la clase Abonado.
     * 
     * <b>pre: </b>: El nombre y dni tienen que estar definidos y no ser vacíos
     * 
     * @param nombre El nombre del abonado a crear
     * @param dni    El DNI del abonado a crear
     */

    public Abonado(String nombre, String dni) {
        assert nombre != null && !nombre.isEmpty();
        assert dni != null && !dni.isEmpty();
        this.nombre = nombre;
        this.dni = dni;
        this.contratos = new ArrayList<IContrato>();
        this.facturas = new ArrayList<IFactura>();
        assert (contratos != null);
    }

    @Override
    public ServicioTecnico iniciarService(Tecnico tecnico) throws ServicioEnCursoException {
        if (!this.tieneService()) {
            this.service = new ServicioTecnico(this, tecnico);
            this.threadServicio = new Thread(this.service);
            this.threadServicio.start();
            return service;
        }

        throw new ServicioEnCursoException(this.dni);
    }

    public ServicioTecnico getServicioTecnico() {
        return this.service;
    }

    @Override
    public boolean tieneService() {
        return this.threadServicio != null && this.threadServicio.isAlive();
    }

    /**
     * Agrega un contrato a la lista del abonado <br>
     * 
     * <b>pre:</b> La lista de contratos está inicializada
     * 
     * @param contrato El contrato a agregar. No puede ser nulo.
     */
    public void agregaContrato(IContrato contrato) {
        assert (contrato != null);
        this.contratos.add(contrato);
    }

    /**
     * Genera un detalle de los contratos actuales del abonado.
     */
    @Override
    public String getDetalle(IPromocion promo) {
        Iterator<IContrato> iterator = this.getIteratorContratos();
        StringBuilder detalle = new StringBuilder();

        detalle.append("DNI: " + this.dni + "\n");
        detalle.append("NOMBRE: " + this.nombre + "\n\n");

        while (iterator.hasNext()) {
            IContrato contrato = iterator.next();
            detalle.append(contrato.getDetalle(promo));
            detalle.append("\n");
        }

        return detalle.toString();
    }

    public ArrayList<IFactura> getFacturasEmitidas() {
        return this.facturas;
    }

    /**
     * Devuelve una nueva factura con el estado actual de los contratos y la agrega
     */
    public IFactura generarFactura(IPromocion promo, String medioDePago, LocalDate fecha) throws SinContratosException {
        assert promo != null;
        if (this.cantidadDeContratos() == 0) {
            throw new SinContratosException();
        }
        IFactura factura = FacturaFactory.getFactura(this.getDetalle(promo), this.getPagoNeto(promo),
                this.getPagoMedioDePago(promo), medioDePago, fecha);
        this.agregarFactura(factura);
        return factura;
    }

    /**
     * Agrega una nueva factura a la lista de facturas
     */
    public void agregarFactura(IFactura factura) {
        assert factura != null;
        this.facturas.add(factura);
    }

    /**
     * Genera un detalle completo de todas las facturas del abonado.
     */
    public String getDetalleFacturas() {
        String separador = "------------\n";
        String detalle = separador;
        for (IFactura factura : facturas) {
            detalle += factura.getDetalle();
            detalle += separador;
        }

        return detalle;
    }

    /**
     * Obtiene la lista de facturas del abonado.
     * 
     * @return La lista de facturas.
     */
    public ArrayList<IFactura> getFacturas() {
        return facturas;
    }

    /**
     * Obtiene la lista de contratos del abonado.
     * 
     * @return La lista de contratos.
     */
    @Override
    public ArrayList<IContrato> getContratos() {
        return contratos;
    }

    /**
     * Devuelve un iterator para las facturas del abonado.
     */
    protected Iterator<IFactura> getIteratorFacturas() {
        return this.getFacturas().iterator();
    }

    /**
     * Devuelve un iterator para los contratos del abonado.
     */
    protected Iterator<IContrato> getIteratorContratos() {
        return this.getContratos().iterator();
    }

    /**
     * Obtiene el valor de los contratos del abonado
     * 
     * @param promo La promoción a aplicar
     */
    @Override
    public double getPagoNeto(IPromocion promo) {
        assert promo != null;

        IContrato contrato;
        Iterator<IContrato> iterator = getIteratorContratos();
        Double pagoNeto = 0.0;
        while (iterator.hasNext()) {
            contrato = iterator.next();
            pagoNeto = pagoNeto + contrato.getPrecio(promo);
        }
        return pagoNeto;
    }

    public boolean tieneContrato(IContrato contrato) {
        assert contrato != null;
        assert this.contratos != null;
        return this.contratos.contains(contrato);
    }

    public boolean tieneContrato(String domicilio) {
        assert domicilio != null && !domicilio.isEmpty();
        assert this.contratos != null;

        for (IContrato contrato : contratos) {
            if (contrato.getDomicilio().equals(domicilio)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Obtiene la cantidad de facturas del abonado
     */
    @Override
    public int cantidadDeFacturas() {
        return this.facturas.size();
    }

    /**
     * Obtiene la cantidad de contratos del abonado
     */
    @Override
    public int cantidadDeContratos() {
        return this.contratos.size();
    }

    /**
     * Obtiene la cantidad de facturas seguidas sin parar hasta un maximo de 2
     *
     * @return La cantidad de facturas seguidas sin parar hasta un maximo de 2
     */
    public int cantidadFacturasSinPagarSeguidas() {
        int i = 0;
        Iterator<IFactura> it = this.getFacturas().iterator();
        while (i < 2 && it.hasNext()) {
            if (!it.next().isPagada())
                i++;
            else
                i = 0;
        }

        return i;
    }

    /**
     * Devuelve un clon del abonado.
     * 
     * @throws CloneNotSupportedException Si no se pudo clonar. Es el caso para
     *                                    abonados de tipo jurídico.
     */
    @Override
    public IAbonado clone() throws CloneNotSupportedException {
        Abonado abonadoClonado = (Abonado) super.clone();
        abonadoClonado.contratos = new ArrayList<IContrato>();
        abonadoClonado.facturas = new ArrayList<IFactura>();

        Iterator<IContrato> iteratorContratos = this.getIteratorContratos();
        Iterator<IFactura> iteratorFacturas = this.getIteratorFacturas();

        while (iteratorContratos.hasNext()) {
            IContrato contrato = (IContrato) iteratorContratos.next().clone();
            abonadoClonado.contratos.add(contrato);
        }

        while (iteratorFacturas.hasNext()) {
            IFactura factura = (IFactura) iteratorFacturas.next().clone();
            abonadoClonado.facturas.add(factura);
        }

        return abonadoClonado;
    }

    /**
     * Obtiene el DNI del abonado
     * 
     * @return El DNI del abonado
     */
    public String getDni() {
        return this.dni;
    }

    /**
     * Obtiene el nombre del abonado
     * 
     * @return El nombre del abonado
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve verdadero si dos abonados se consideran iguales. Son iguales si
     * coincide su DNI.<br>
     * 
     * @param obj El objeto a comparar.
     * @return Verdadero si son iguales.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Abonado)) {
            return false;
        }

        Abonado abonado = (Abonado) obj;

        return this.dni.equals(abonado.getDni());
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setContratos(ArrayList<IContrato> contratos) {
        this.contratos = contratos;
    }

    public void setFacturas(ArrayList<IFactura> facturas) {
        this.facturas = facturas;
    }

    public String toString() {
        String contratosString = "";
        for (IContrato contrato : contratos) {
            contratosString = contratosString + "\n" + contrato.toString() + "\n";
        }
        return contratosString + "\n facturas: " + this.getDetalleFacturas() + " \n dni: " + this.dni + "\n nombre: "
                + this.nombre;
    }

    public void eliminaContrato(IContrato contrato) {
        this.contratos.remove(contrato);
    }

    @Override
    public boolean isFisico(){
        return false;
    }


}
