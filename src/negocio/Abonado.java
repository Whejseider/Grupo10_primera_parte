package negocio;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.interfaces.IAbonado;
import negocio.interfaces.IContrato;
import negocio.interfaces.IPromocion;

public abstract class Abonado implements IAbonado {
    private String nombre;
    private String dni;
    private ArrayList<IContrato> contratos;

    /**
     * Constructor de la clase Abonado.
     *  
     * <b>pre: </b>: El nombre y dni tienen que estar definidos y no ser vacíos
     * 
     * @param nombre El nombre del abonado a crear
     * @param dni El DNI del abonado a crear
     */
    public Abonado(String nombre, String dni) {
        assert nombre != null && !nombre.isEmpty();
        assert dni != null && !dni.isEmpty();
        this.nombre = nombre;
        this.dni = dni;
        this.contratos = new ArrayList<IContrato>();
        assert(contratos != null);
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

    @Override
    public String getDetalle() {
        return null;
    }

    /**
     * Obtiene la lista de contratos del abonado.
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

    /**
     * Obtiene la cantidad de contratos del abonado
     */
    @Override
    public int cantidadDeContratos() {
        return this.contratos.size();
    }

    /**
     * Devuelve un clon del abonado. 
     * @throws CloneNotSupportedException Si no se pudo clonar. Es el caso para abonados de tipo jurídico.
     */
    @Override
    public IAbonado clone() throws CloneNotSupportedException {
        Abonado abonadoClonado = (Abonado) super.clone();
        abonadoClonado.contratos.clear();

        Iterator<IContrato> iterator = this.getIteratorContratos();

        while (iterator.hasNext()) {
            IContrato contrato = (IContrato) iterator.next().clone();
            abonadoClonado.contratos.add(contrato);
        }

        return abonadoClonado;
    }
    
    /**
     * Obtiene el DNI del abonado
     * @return El DNI del abonado
     */
    public String getDni() {
    	return this.dni;
    }
    
    /**
     * Obtiene el nombre del abonado
     * @return El nombre del abonado
     */
    public String getNombre() {
    	return this.nombre;
    }
    
    /**
     * Devuelve verdadero si dos abonados se consideran iguales. Son iguales si coincide su DNI.<br>
     * 
     * @param obj El objeto a comparar.
     * @return Verdadero si son iguales.
     */
    @Override
    public boolean equals(Object obj)
    {
    	
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Abonado)) {
            return false;
        }

        Abonado abonado = (Abonado) obj;

        return this.dni.equals(abonado.getDni());
    }
}
