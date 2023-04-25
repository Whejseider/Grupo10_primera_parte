package modelo.pago;

import modelo.abonado.IAbonado;
import modelo.contrato.IContrato;
import modelo.factura.Facturable;

import java.util.List;


public abstract class PagoDecorator implements Facturable {
    protected Facturable factura;

    public PagoDecorator(Facturable factura) {
        super();
        this.factura = factura;
    }

    @Override
    public void setTotalAPagar(double totalAPagar) {
        this.factura.setTotalAPagar(totalAPagar);
    }

    @Override
    public void setTotalAPagarMdP(double totalAPagar) {
        this.factura.setTotalAPagarMdP(totalAPagar);
    }

    public Facturable getFactura() {
        return factura;
    }

    public void setFactura(Facturable factura) {
        this.factura = factura;
    }

    @Override
    public double getTotalAPagar() {
        return this.factura.getTotalAPagar();
    }

    @Override
    public String getDetalles() {
        return this.factura.getDetalles();
    }

    @Override
    public IAbonado getAbonado() {
        return this.factura.getAbonado();
    }

    @Override
    public void setAbonado(IAbonado abonado) {
        this.factura.setAbonado(abonado);
    }

    @Override
    public List<IContrato> getContrataciones() {
        return this.factura.getContrataciones();
    }

    @Override
    public String getMedioDePago() {
        return this.factura.getMedioDePago();
    }

    @Override
    public double getTotalAPagarMdP() {
        return this.factura.getTotalAPagarMdP();
    }

    @Override
    public void setMedioDePago(String medioDePago) {
        this.factura.setMedioDePago(medioDePago);
    }

    @Override
    public Object clone() {
        return this.factura.clone();
    }


}
