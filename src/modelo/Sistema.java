package modelo;

import modelo.excepciones.*;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import modelo.tecnicos.ServicioTecnico;
import modelo.tecnicos.Tecnico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Singleton del sistema. El sistema se encarga de la creación
 * y gestión de contratos, abonados, facturas y tecnicos, proporcionando una interfaz
 * unificada para el mundo del problema.
 */
public class Sistema {
    private static Sistema instance = null;

    private ArrayList<IAbonado> abonados = new ArrayList<IAbonado>();;
    private ArrayList<Tecnico> tecnicos = new ArrayList<Tecnico>();
    private IPromocion promocionActiva = new SinPromocion();
    private LocalDate fecha = LocalDate.now();

    private Sistema() {
    }

    public void setAbonados(ArrayList<IAbonado> abonados) {
        this.abonados = abonados;
    }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public IPromocion getPromocionActiva() {
        return promocionActiva;
    }

    public void setPromocionActiva(IPromocion promocionActiva) {
        this.promocionActiva = promocionActiva;
    }

    /**
     * Obtiene la instancia del sistema
     *
     * @return
     */
    public static Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Agrega un nuevo abonado al sistema. Se inicializará sin contratos o facturas.
     *
     * @param tipo   El tipo de abonado. "Fisico" o "Juridico"
     * @param nombre El nombre del abonado
     * @param dni    El dni del abonado
     * @throws AbonadoDuplicadoException Si el DNI ya coincide con el de un abonado
     *                                   en
     *                                   el sistema
     * @return El abonado creado
     */
    public IAbonado agregarAbonado(String tipo, String nombre, String dni) throws AbonadoDuplicadoException {
        IAbonado abonado = AbonadoFactory.getAbonado(tipo, nombre, dni);
        if (!abonados.contains(abonado))
            abonados.add(abonado);
        else
            throw new AbonadoDuplicadoException(abonado);

        return abonado;
    }

    /**
     * Elimina un abonado del sistema. Todas sus facturas y contratos se perderán.
     *
     * @param dni El dni del abonado a eliminar.
     */
    public void eliminarAbonado(String dni) {
        for (IAbonado abonado : abonados) {
            if (abonado.getDni().equals(dni)) {
                abonados.remove(abonado);
                return;
            }
        }
    }

    /**
     * Establece la promoción actual. Todas las facturas que se generen mientras
     * haya una
     * promoción activa, aplicarán el descuento.
     *
     * @param promocion La promoción a aplicar
     */
    public void setPromocion(IPromocion promocion) {
        this.promocionActiva = promocion;
    }

    public IPromocion getPromocion() {
        return this.promocionActiva;
    }

    /**
     * Obtiene una lista con todos los abonados activos del sistema.
     * 
     * @return
     */
    public ArrayList<IAbonado> getAbonados() {
        return abonados;
    }

    /**
     * Genera una factura para un abonado, que se genera con los contratos activos
     * de este.
     *
     * @param dni         El dni del abonado
     * @param medioDePago El medio de pago. Puede ser cheque, tarjeta o efectivo.
     * @return La factura creada.
     * @throws SinContratosException    Si el abonado no tiene contratos
     * @throws AbonadoNoExisteException Si no se pudo encontrar el abonado.
     */
    public IFactura generarFactura(String dni, String medioDePago)
            throws SinContratosException, AbonadoNoExisteException {
        IAbonado abonado = this.getAbonado(dni);

        return abonado.generarFactura(this.promocionActiva, medioDePago, this.fecha);
    }

    /**
     * Obtiene un abonado por dni
     *
     * @param dni El dni del abonado a buscar
     * @return El abonado encontrado
     * @throws AbonadoNoExisteException SI no se encontró un abonado con el mismo
     *                                  DNI.
     */
    public IAbonado getAbonado(String dni) throws AbonadoNoExisteException {
        for (IAbonado abonado : abonados)
            if (abonado.getDni().equals(dni))
                return abonado;

        throw new AbonadoNoExisteException(dni);
    }

    /**
     * Busca si existe un contrato en el sistema. Si el domicilio del contrato
     * coincide
     * con alguno del sistema, se considera ya existente.
     *
     * @param contrato El contrato a buscar
     * @return Verdadero si se encuentra el contrato
     */
    private boolean contratoExiste(IContrato contrato) {
        for (IAbonado abonado : abonados)
            if (abonado.tieneContrato(contrato))
                return true;
        return false;
    }

    /**
     * Agrega un nuevo contrato a un abonado<br>
     * Si es abonado fisico hace uso del patron State
     *
     * @param domicilio   El domicilio de contrato
     * @param tieneMovil  Si el contrato tiene movil
     * @param cantCamaras Cantidad de camaras a asignarse al contrato
     * @param cantBotones Cantidad de botones a asignarse al contrato
     * @throws ContratoDuplicadoException Si existe un contrato con el mismo
     *                                    domicilio
     * @throws AbonadoNoExisteException   Si no se pudo encontrar un abonado con el
     *                                    dni pasado.
     */
    public void agregarContrato(String dni, String tipo, String domicilio, boolean tieneMovil, int cantCamaras,
            int cantBotones) throws ContratoDuplicadoException, AbonadoNoExisteException {

        IContrato nuevoContrato = ContratoFactory.getContrato(tipo, domicilio, tieneMovil, cantBotones, cantCamaras);
        if (this.contratoExiste(nuevoContrato))
            throw new ContratoDuplicadoException(nuevoContrato);

        getAbonado(dni).contratarNuevoServicio(nuevoContrato);

    }

    public void eliminarContrato(String domicilio) {
        for (IAbonado abonado : abonados) {
            List<IContrato> contratos = abonado.getContratos();
            Iterator<IContrato> it = contratos.iterator();
            while (it.hasNext()) {
                IContrato contrato = it.next();
                if (contrato.getDomicilio().equals(domicilio)) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Obtiene todas las facturas emitidas por los abonados activos del sistema.
     *
     * @return Una coleccion con las facturas.
     */
    public ArrayList<IFactura> getFacturasEmitidas() {
        ArrayList<IFactura> facturas = new ArrayList<>();
        for (IAbonado abonado : this.abonados)
            if (abonado.cantidadDeFacturas() > 0)
                facturas.addAll(abonado.getFacturasEmitidas());
        return facturas;
    }

    public void agregarTecnico(String nombre) throws TecnicoYaExisteException {
        if (this.getTecnico(nombre) != null) {
            throw new TecnicoYaExisteException();
        }
        this.tecnicos.add(new Tecnico(nombre));
    }

    public void eliminarTecnico(String nombre) throws TecnicoTrabajandoException, TecnicoNoExisteException {
        Tecnico tecnico = this.getTecnico(nombre);

        if (tecnico == null) {
            throw new TecnicoNoExisteException();
        }

        if (!tecnico.isDisponible()) {
            throw new TecnicoTrabajandoException();
        }

        this.tecnicos.remove(this.getTecnico(nombre));
    }

    public Tecnico getTecnico(String nombre) {
        for (Tecnico tecnico : tecnicos) {
            if (tecnico.getNombre().equals(nombre)) {
                return tecnico;
            }
        }

        return null;
    }

    public ServicioTecnico pedirService(String dniAbonado, String nombreTecnico)
            throws AbonadoNoExisteException, ServicioEnCursoException {
        IAbonado abonado = this.getAbonado(dniAbonado);
        Tecnico tecnico = this.getTecnico(nombreTecnico);

        return abonado.iniciarService(tecnico);
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }
}