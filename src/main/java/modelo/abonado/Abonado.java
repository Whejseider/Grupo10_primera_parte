package modelo.abonado;

import modelo.contrato.Contrato;
import modelo.contrato.IContrato;
import modelo.domicilio.Domicilio;

import java.util.ArrayList;
import java.util.List;

public abstract class Abonado implements IAbonado {
    private String nombre;
    private final String dni;
    private List<IContrato> contratos;
    private List<Domicilio> domicilios;

    /**
     * Constructor de la clase Abonado<br>
     * Recibe como parametro un nombre y un dni
     * @param nombre nombre del abonado
     * @param dni del abonado
     */
    public Abonado(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.contratos = new ArrayList<>();
        this.domicilios = new ArrayList<>();
    }

    @Override
    public abstract String getTipo();

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getDni() {
        return this.dni;
    }

    @Override
    public List<IContrato> getContratos() {
        return this.contratos;
    }

    @Override
    public List<Domicilio> getDomicilios() {
        return this.domicilios;
    }

    /**
     * Devuelve el precio a pagar de un servicio figurado en un contrato<br>
     * @param contrato El contrato que se desea saber el precio a pagar
     * @param i Numero de iteracion usado para persona juridica
     * @return el Precio a pagar de un servicio dado un contrato
     */
    @Override
    public double getPagoDeServicio(IContrato contrato, int i) {
        return contrato.getPrecio();
    }

    @Override
    public void agregarContrato(IContrato contrato) {
        this.contratos.add(contrato);
    }

    @Override
    public void eliminarContrato(IContrato contrato) {
        this.contratos.remove(contrato);
    }

    @Override
    public void agregarDomicilio(Domicilio domicilio) {
        this.domicilios.add(domicilio);
    }

    @Override
    public void eliminarDomicilio(Domicilio domicilio) {
        this.domicilios.remove(domicilio);
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Abonado abonadoClon = null;
        try {
            abonadoClon = (Abonado) super.clone();
            abonadoClon.contratos = new ArrayList<>(contratos.size());
            for (IContrato contrato : contratos) {
                abonadoClon.contratos.add((IContrato) contrato.clone());
            }
            abonadoClon.domicilios = new ArrayList<>(domicilios.size());
            for (Domicilio domicilio : domicilios) {
                abonadoClon.domicilios.add((Domicilio) domicilio.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return abonadoClon;
    }

}
