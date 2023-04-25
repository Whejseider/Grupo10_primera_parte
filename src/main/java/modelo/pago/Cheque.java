package modelo.pago;

import modelo.factura.Facturable;

public class Cheque extends PagoDecorator {
    public Cheque(Facturable factura) {
        super(factura);
        this.factura.setMedioDePago("Cheque");
        this.factura.setTotalAPagarMdP(this.factura.getTotalAPagarMdP()*descEfectivo);
    }

//    @Override
//    public double getTotalAPagarMdP() {
//        return this.factura.getTotalAPagarMdP()*descEfectivo;
//    }

}
