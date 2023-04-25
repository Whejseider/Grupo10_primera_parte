package modelo.pago;

import modelo.factura.Facturable;

public class Tarjeta extends PagoDecorator {
    public Tarjeta(Facturable factura) {
        super(factura);
//        this.factura.setMedioDePago("Tarjeta");
//        this.factura.setTotalAPagarMdP(this.factura.getTotalAPagarMdP()*descEfectivo);
    }


    @Override
    public double getTotalAPagarMdP() {
        return this.factura.getTotalAPagarMdP()*descEfectivo;
    }

    @Override
    public String getMedioDePago() {
        return "Tarjeta";
    }

    @Override
    public String getDetalles() {
        String detalles = "Descuento por medio de pago (" + getMedioDePago() + "): " + (this.factura.getTotalAPagar() - getTotalAPagarMdP()) + "\n" +
                "Total a pagar con descuento por medio de pago: " + getTotalAPagarMdP() + "\n";
        return this.factura.getDetalles() + detalles;
    }
}
