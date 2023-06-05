package modelo.decorators;

import java.util.ArrayList;

import modelo.excepciones.SinContratosException;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;

/**
 * Clase abstracta base para crear decoradores de pago.
 */
public abstract class PagoDecorator implements IFactura {
    private IFactura factura;

    /**
     * Crea un nuevo decorator a partir de una clase que implemente IFactura.
     *
     * @param factura El facturable a decorar
     */
    public PagoDecorator(IFactura factura) {
        assert factura != null : "El facturable no puede ser nulo";

        this.factura = factura;
    }

    public IFactura getFactura() {
        return factura;
    }

    public void setFactura(IFactura factura) {
        this.factura = factura;
    }

    @Override
    public String getConcepto() {
        return this.factura.getConcepto();
    }

    @Override
    public double getSubtotal() {
        return this.factura.getSubtotal();
    }

    @Override
    public void setValorNeto(double valorNeto) {
        this.factura.setValorNeto(valorNeto);
    }

    @Override
    public IFactura clone() throws CloneNotSupportedException {

        PagoDecorator clonDecorator = (PagoDecorator) super.clone();
        clonDecorator.factura = factura.clone();
        return clonDecorator;
    }
}