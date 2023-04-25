package modelo.pago;

import modelo.factura.Facturable;

public class Efectivo extends PagoDecorator {

    public Efectivo(Facturable factura) {
        super(factura);
        this.factura.setMedioDePago("Efectivo");
        this.factura.setTotalAPagarMdP(this.factura.getTotalAPagar()*descEfectivo);

    }

//    @Override
//    public double getTotalAPagarMdP() {
//        return this.factura.getTotalAPagarMdP()*descEfectivo;
//    }
}
