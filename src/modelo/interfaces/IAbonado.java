package modelo.interfaces;

import modelo.excepciones.ServicioEnCursoException;
import modelo.excepciones.SinContratosException;
import modelo.tecnicos.ITecnico;
import modelo.tecnicos.ServicioTecnico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IAbonado extends Cloneable, IDetallable, Serializable {

    void agregaContrato(IContrato contrato);

    void eliminaContrato(IContrato contrato);

    int cantidadDeContratos();

    int cantidadDeFacturas();

    boolean tieneContrato(IContrato contrato);

    boolean tieneContrato(String domicilio);

    ServicioTecnico iniciarService(ITecnico tecnico) throws ServicioEnCursoException;

    ServicioTecnico getServicioTecnico();

    boolean tieneService();

    String getDni();

    String getNombre();

    IAbonado clone() throws CloneNotSupportedException;

    /**
     * Obtiene el pago neto previo a cualquier descuento sobre el total.
     *
     * @param promo La promoción a aplicar
     * @return El precio total.
     */
    double getPagoNeto(IPromocion promo);

    /**
     * Obtiene el pago total calculado con el medio de pago
     *
     * @param promo La promoción a aplicar
     * @return El precio total descontado.
     */
    double getPagoMedioDePago(IPromocion promo);

    IFactura generarFactura(IPromocion promo, String medioDePago, LocalDate fecha) throws SinContratosException;

    IContrato buscaContrato(String domicilio);

    void agregarFactura(IFactura factura);

    String getDetalleFacturas();

    ArrayList<IFactura> getFacturasEmitidas();

    ArrayList<IContrato> getContratos();

    void pagarFactura(IFactura factura);

    void actualizarFactura(IFactura factura, IFactura facturaVieja);

    void contratarNuevoServicio(IContrato contrato);

    void bajaDeServicio(IContrato contrato);

    boolean isFisico();

    void actualizadorEstado();

    boolean existeFactura(IFactura factura);
}
