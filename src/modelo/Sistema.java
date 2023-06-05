package modelo;

import java.util.ArrayList;

import modelo.decorators.PagoChequeDecorator;
import modelo.decorators.PagoEfectivoDecorator;
import modelo.decorators.PagoTarjetaCreditoDecorator;
import modelo.excepciones.AbonadoDuplicadoException;
import modelo.excepciones.AbonadoNoExisteException;
import modelo.excepciones.ContratoDuplicadoException;
import modelo.excepciones.SinContratosException;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;

/**
 * Singleton del sistema. El sistema se encarga de la creación 
 * y gestión de contratos y abonados, proporcionando una interfaz
 * unificada para el mundo del problema.
 */
public class Sistema {
    private static Sistema instance = null;
    private ArrayList<IAbonado> abonados;
    private IPromocion promocionActiva = new SinPromocion();

    private Sistema() {
        abonados = new ArrayList<IAbonado>();
    }

    /**
     * Obtiene la instancia del sistema
     * @return
     */
    public static Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    /**
     * Agrega un nuevo abonado al sistema. Se inicializará sin contratos o facturas.
     * @param tipo El tipo de abonado. "Fisico" o "Juridico"
     * @param nombre El nombre del abonado
     * @param dni El dni del abonado
     * @throws AbonadoDuplicadoException Si el DNI ya coincide con el de un abonado en
     * el sistema
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
     * Establece la promoción actual. Todas las facturas que se generen mientras haya una 
     * promoción activa, aplicarán el descuento.
     * @param promocion La promoción a aplicar
     */
    public void setPromocion(IPromocion promocion) {
        this.promocionActiva = promocion;
    }

    /**
     * Obtiene una lista con todos los abonados activos del sistema.
     * @return
     */
    public ArrayList<IAbonado> getAbonados() {
        return abonados;
    }

    /**
     * Genera una factura para un abonado, que se genera con los contratos activos de este.
     * @param dni El dni del abonado
     * @param medioDePago El medio de pago. Puede ser cheque, tarjeta o efectivo.
     * @return La factura creada. 
     * @throws SinContratosException Si el abonado no tiene contratos
     * @throws AbonadoNoExisteException Si no se pudo encontrar el abonado.
     */
    public IFactura generarFactura(String dni, String medioDePago) throws SinContratosException, AbonadoNoExisteException {
        IAbonado abonado = this.getAbonado(dni);
        switch (medioDePago) {
            case "cheque":
                abonado = new PagoChequeDecorator(abonado);
                break;
            case "tarjeta":
                abonado = new PagoTarjetaCreditoDecorator(abonado);
                break;
            default:
                abonado = new PagoEfectivoDecorator(abonado);
                break;
        }
        return abonado.generarFactura(this.promocionActiva);
    }

    /**
     * Obtiene un abonado por dni
     * @param dni El dni del abonado a buscar
     * @return El abonado encontrado
     * @throws AbonadoNoExisteException SI no se encontró un abonado con el mismo DNI.
     */
    public IAbonado getAbonado(String dni) throws AbonadoNoExisteException {
        for (IAbonado abonado : abonados)
            if (abonado.getDni().equals(dni))
                return abonado;
        
        throw new AbonadoNoExisteException(dni);
    }

    /**
     * Busca si existe un contrato en el sistema. Si el domicilio del contrato coincide
     * con alguno del sistema, se considera ya existente.
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
     * Agrega un nuevo contrato a un abonado
     * 
     * @param domicilio   El domicilio de contrato
     * @param tieneMovil  Si el contrato tiene movil
     * @param cantCamaras Cantidad de camaras a asignarse al contrato
     * @param cantBotones Cantidad de botones a asignarse al contrato
     * @throws ContratoDuplicadoException Si existe un contrato con el mismo domicilio
     * @throws AbonadoNoExisteException Si no se pudo encontrar un abonado con el dni pasado.
     */
    public void agregarContrato(String dni, String tipo, String domicilio, boolean tieneMovil, int cantCamaras,
            int cantBotones) throws ContratoDuplicadoException, AbonadoNoExisteException {

        IContrato nuevoContrato = ContratoFactory.getContrato(tipo, domicilio, tieneMovil, cantBotones, cantCamaras);
        if (this.contratoExiste(nuevoContrato))
            throw new ContratoDuplicadoException(nuevoContrato);

        getAbonado(dni).agregaContrato(nuevoContrato);

    }

    /**
     * Obtiene todas las facturas emitidas por los abonados activos del sistema.
     * @return Una coleccion con las facturas.
     */
    public ArrayList<IFactura> getFacturasEmitidas() {
        ArrayList<IFactura> facturas = new ArrayList<>();
        for (IAbonado abonado : this.abonados)
            if (abonado.cantidadDeFacturas() > 0)
                facturas.addAll(abonado.getFacturasEmitidas());
        return facturas;
    }
}