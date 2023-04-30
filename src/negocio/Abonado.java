package negocio;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.interfaces.IAbonado;
import negocio.interfaces.IContrato;
import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

public abstract class Abonado implements IAbonado {
    private String nombre;
    private String dni;
    private ArrayList<IContrato> contratos;
    private ArrayList<IFactura> facturas;

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
        detalle.append("NOMBRE: " + this.nombre + "\n");

        while (iterator.hasNext()) {
            IContrato contrato = iterator.next();
            detalle.append(contrato.getDetalle(promo));
            detalle.append("\n");
        }

        return detalle.toString();
    }

    /**
     * Devuelve una nueva factura con el estado actual de los contratos
     */
    public IFactura generarFactura(IPromocion promo) {
        return new Factura(this.getDetalle(promo), this.getPagoNeto(promo), this.getPagoMedioDePago(promo));
    }

    /**
     * Agrega una nueva factura a la lista de facturas
     */
    public void agregarFactura(IFactura factura) {
        assert factura != null;
        this.facturas.add(factura);
    }

    /**
     * Genera una factura con los el estado actual de los contratos del abonado, y
     * se agrega a la lista de facturas.
     */
    public void facturar(IPromocion promo) {
        assert promo != null;
        this.agregarFactura(this.generarFactura(promo));
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
     * Obtiene la lista de contratos del abonado.
     * 
     * @return La lista de contratos.
     */
    public ArrayList<IContrato> getContratos() {
        return contratos;
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
        Iterator<IContrato> iterator = this.getIteratorContratos();

        while (iterator.hasNext()) {
            if (contrato.equals(iterator.next()))
                return true;
        }

        return false;
    }

    /**
     * Obtiene la cantidad de contratos del abonado
     */
    @Override
    public int cantidadDeContratos() {
        return this.contratos.size();
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

        Iterator<IContrato> iterator = this.getIteratorContratos();

        while (iterator.hasNext()) {
            IContrato contrato = (IContrato) iterator.next().clone();
            abonadoClonado.contratos.add(contrato);
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

    public String toString() {
        return "\n dni: " + this.dni + "\n nombre: " + this.nombre;
    }
}
