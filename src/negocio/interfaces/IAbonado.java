package negocio.interfaces;

import java.util.ArrayList;

import negocio.excepciones.SinContratosException;

public interface IAbonado extends Cloneable, IDetallable {

    public void agregaContrato(IContrato contrato);

    public int cantidadDeContratos();

    public int cantidadDeFacturas();

    public boolean tieneContrato(IContrato contrato);

    public String getDni();

    public String getNombre();

    public IAbonado clone() throws CloneNotSupportedException;

    /**
     * Obtiene el pago neto previo a cualquier descuento sobre el total.
     * 
     * @param promo La promoción a aplicar
     * @return El precio total.
     */
    public double getPagoNeto(IPromocion promo);

    /**
     * Obtiene el pago total calculado con el medio de pago
     * 
     * @param promo La promoción a aplicar
     * @return El precio total descontado.
     */
    public double getPagoMedioDePago(IPromocion promo);

    public IFactura generarFactura(IPromocion promo) throws SinContratosException;

    public void agregarFactura(IFactura factura);

    public String getDetalleFacturas();

    public ArrayList<IFactura> getFacturasEmitidas();

}
