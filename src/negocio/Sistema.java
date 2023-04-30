package negocio;

import java.util.ArrayList;

import negocio.decorators.PagoChequeDecorator;
import negocio.decorators.PagoDecorator;
import negocio.decorators.PagoEfectivoDecorator;
import negocio.decorators.PagoTarjetaCreditoDecorator;
import negocio.excepciones.AbonadoDuplicadoException;
import negocio.excepciones.ContratoDuplicadoException;
import negocio.interfaces.IAbonado;
import negocio.interfaces.IContrato;
import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

public class Sistema {
    private static Sistema instance = null;
    private ArrayList<IAbonado> abonados;
    private IPromocion promocionActiva;

    private Sistema() {
        abonados = new ArrayList<IAbonado>();
    }

    public static Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    public void agregarAbonado(String tipo, String nombre, String dni) throws AbonadoDuplicadoException {
        IAbonado abonado = AbonadoFactory.getAbonado(tipo, nombre, dni);
        if (!abonados.contains(abonado))
            abonados.add(abonado);
        else
            throw new AbonadoDuplicadoException(abonado);
    }

    public void eliminarAbonado(String dni) {
        for (IAbonado abonado : abonados) {
            if (abonado.getDni().equals(dni)) {
                abonados.remove(abonado);
                return;
            }
        }
    }

    public void setPromocion(IPromocion promocion) {
        this.promocionActiva = promocion;
    }

    public ArrayList<IAbonado> getAbonados() {
        return abonados;
    }

    public IFactura generarFactura(String dni, String medioDePago) throws Exception {
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

    public IAbonado getAbonado(String dni) throws Exception {
        for (IAbonado abonado : abonados)
            if (abonado.getDni().equals(dni))
                return abonado;
        throw new Exception("abonado no existe");
    }

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
     * @throws Exception
     */
    public void agregarContrato(String dni, String tipo, String domicilio, boolean tieneMovil, int cantCamaras,
            int cantBotones) throws Exception {

        IContrato nuevoContrato = ContratoFactory.getContrato(tipo, domicilio, tieneMovil, cantBotones, cantCamaras);
        if (this.contratoExiste(nuevoContrato))
            throw new ContratoDuplicadoException(nuevoContrato);

        getAbonado(dni).agregaContrato(nuevoContrato);

    }
}