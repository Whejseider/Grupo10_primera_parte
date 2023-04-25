package modelo.pago;

import modelo.factura.Facturable;

public class Tarjeta extends PagoDecorator {
    public Tarjeta(Facturable factura) {
        super(factura);
        this.factura.setMedioDePago("Tarjeta");
        this.factura.setTotalAPagarMdP(this.factura.getTotalAPagarMdP()*descEfectivo);
    }


//    @Override
//    public double getTotalAPagarMdP() {
//        return this.factura.getTotalAPagarMdP()*descEfectivo;
//    }
}
