package modelo;

import modelo.excepciones.*;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import modelo.tecnicos.ITecnico;
import modelo.tecnicos.ServicioTecnico;

import modelo.tecnicos.TecnicoFactory;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Singleton del sistema. El sistema se encarga de la creación
 * y gestión de contratos, abonados, facturas y tecnicos, proporcionando una interfaz
 * unificada para el mundo del problema.
 */
public class Sistema {
    private static Sistema instance = null;

    private ArrayList<IAbonado> abonados = new ArrayList<IAbonado>();;
    private ArrayList<ITecnico> tecnicos = new ArrayList<ITecnico>();
    private IPromocion promocionActiva = new SinPromocion();
    private LocalDate fecha = LocalDate.now();

    private Sistema() {
    }

    /**
     * Obtiene la instancia del sistema
     *
     * @return La instancia singleton de Sistema
     */
    public static Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    /**
     * Agrega un nuevo abonado al sistema. Se inicializará sin contratos o facturas.
     *
     * @param tipo   El tipo de abonado. "Fisico" o "Juridico"
     * @param nombre El nombre del abonado
     * @param dni    El dni del abonado
     * @return El abonado creado
     * @throws AbonadoDuplicadoException Si el DNI ya coincide con el de un abonado
     *                                   en
     *                                   el sistema
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
     * @return Una lista con los abonados
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
        IFactura factura = abonado.generarFactura(this.promocionActiva, medioDePago, this.fecha);
        this.actualizadorEstado();
        return factura;
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

    /**
     * Agrega un nuevo tecnico al sistema
     * @param nombre El nombre del tecnico
     * @throws TecnicoYaExisteException Si ya existe un tecnico con ese nombre
     */
    public void agregarTecnico(String nombre) throws TecnicoYaExisteException {
        if (this.getTecnico(nombre) != null) {
            throw new TecnicoYaExisteException();
        }
        this.tecnicos.add(TecnicoFactory.getTecnico(nombre));
    }

    /**
     * Elimina un tecnico del sistema
     * @param nombre El nombre del tecnico
     * @throws TecnicoTrabajandoException Si el tecnico está trabajando en algún service
     * @throws TecnicoNoExisteException Si el tecnico con ese nombre no existe
     */
    public void eliminarTecnico(String nombre) throws TecnicoTrabajandoException, TecnicoNoExisteException {
        ITecnico tecnico = this.getTecnico(nombre);

        if (tecnico == null) {
            throw new TecnicoNoExisteException();
        }

        if (!tecnico.isDisponible()) {
            throw new TecnicoTrabajandoException();
        }

        this.tecnicos.remove(this.getTecnico(nombre));
    }

    /**
     * Obtiene un tecnico a partir del nombre
     * @param nombre El nombre del tecnico
     * @return El tecnico o null si no se encontró
     */
    public ITecnico getTecnico(String nombre) {
        for (ITecnico tecnico : tecnicos) {
            if (tecnico.getNombre().equals(nombre)) {
                return tecnico;
            }
        }

        return null;
    }

    /**
     * Pide inicializar un service a un abonado
     * @param dniAbonado El dni del abonado a mandar el service
     * @param nombreTecnico El nombre del tecnico a asignar
     * @return El servicio creado
     * @throws AbonadoNoExisteException Si el abonado con el dni pasado no existe
     * @throws ServicioEnCursoException Si el abonado ya tiene un servicio técnico en curso
     */
    public ServicioTecnico pedirService(String dniAbonado, String nombreTecnico)
            throws AbonadoNoExisteException, ServicioEnCursoException {
        IAbonado abonado = this.getAbonado(dniAbonado);
        ITecnico tecnico = this.getTecnico(nombreTecnico);

        return abonado.iniciarService(tecnico);
    }

    /**
     * Obtiene los tecnicos activos del sistema
     *
     * @return Una lista con todos los tecnicos
     */
    public ArrayList<ITecnico> getTecnicos() {
        return tecnicos;
    }

    /**
     * Actualiza el estado de todos los abonados.
     */
    public void actualizadorEstado() {
        for (IAbonado abonado : abonados) {
            abonado.actualizadorEstado();
        }
    }

    /**
     * Genera facturas para todos los abonados. No se crean
     * si el abonado no tiene contratos.
     */
    public void generadorFacturas() {
        for (IAbonado abonado : abonados) {
            try {
                if (abonado.cantidadDeContratos() > 0)
                    abonado.generarFactura(getPromocion(), "", getFecha());
            } catch (SinContratosException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Sobreescribe los abonados activos del sistema. SOLO PARA SERIALIZACION.
     * @param abonados Los abonados a usar
     */
    public void setAbonados(ArrayList<IAbonado> abonados) {
        this.abonados = abonados;
    }

    /**
     * Sobreescribe los tecnicos activos del sistema. SOLO PARA SERIALIZACION.
     * @param tecnicos Los tecnicos a usar
     */
    public void setTecnicos(ArrayList<ITecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    /**
     * Obtiene la fecha actual del sistema
     * @return La fecha actual
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Sobreescribe la fecha actual del sistema. SOLO PARA SERIALIZACION.
     * @param fecha La fecha a usar
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}