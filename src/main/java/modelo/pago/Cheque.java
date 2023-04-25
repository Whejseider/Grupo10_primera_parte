package modelo.pago;

import modelo.factura.Facturable;

public class Cheque extends PagoDecorator {
    public Cheque(Facturable factura) {
        super(factura);
//        this.factura.setMedioDePago("Cheque");
//        this.factura.setTotalAPagarMdP(this.factura.getTotalAPagarMdP()*descEfectivo);
    }

    @Override
    public double getTotalAPagarMdP() {
        return this.factura.getTotalAPagarMdP()*descEfectivo;
    }

    @Override
    public String getMedioDePago() {
        return "Cheque";
    }

    @Override
    public String getDetalles() {
        String detalles = "Descuento por medio de pago (" + getMedioDePago() + "): " + (this.factura.getTotalAPagar() - getTotalAPagarMdP()) + "\n" +
                "Total a pagar con descuento por medio de pago: " + getTotalAPagarMdP() + "\n";
        return this.factura.getDetalles() + detalles;
    }

}
