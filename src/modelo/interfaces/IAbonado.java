package modelo.interfaces;

import modelo.excepciones.SinContratosException;

import java.util.ArrayList;

public interface IAbonado extends Cloneable, IDetallable {

    void agregaContrato(IContrato contrato);

    void eliminaContrato(IContrato contrato);

    int cantidadDeContratos();

    int cantidadDeFacturas();

    boolean tieneContrato(IContrato contrato);

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

    IFactura generarFactura(IPromocion promo, String medioDePago) throws SinContratosException;

    void agregarFactura(IFactura factura);

    String getDetalleFacturas();

    ArrayList<IFactura> getFacturasEmitidas();

    ArrayList<IContrato> getContratos();

    void pagarFactura(IFactura factura) ;

    void contratarNuevoServicio(IContrato contrato);


    void bajaDeServicio(IContrato contrato) ;

}
