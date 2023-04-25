package modelo.pago;

import modelo.factura.Facturable;

public class Efectivo extends PagoDecorator {

    public Efectivo(Facturable factura) {
        super(factura);
//        this.factura.setMedioDePago("Efectivo");
//        this.factura.setTotalAPagarMdP(this.factura.getTotalAPagar()*descEfectivo);

    }

    @Override
    public double getTotalAPagarMdP() {
        return this.factura.getTotalAPagarMdP()*descEfectivo;
    }

    @Override
    public String getMedioDePago() {
        return "Efectivo";
    }

    @Override
    public String getDetalles() {
        String detalles = "Descuento por medio de pago (" + getMedioDePago() + "): " + (this.factura.getTotalAPagar() - getTotalAPagarMdP()) + "\n" +
                "Total a pagar con descuento por medio de pago: " + getTotalAPagarMdP() + "\n";
        return this.factura.getDetalles() + detalles;
    }
}
