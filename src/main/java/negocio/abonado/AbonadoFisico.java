package negocio.abonado;

import negocio.interfaces.IPromocion;

public class AbonadoFisico extends Abonado {

    public AbonadoFisico(String nombre, String dni) {
        super(nombre, dni);
    }

    @Override
    public double getPagoMedioDePago(IPromocion promo) {
        return this.getPagoNeto(promo);
    }

    @Override
    public Object clone() {
        Object clon = null;

        try {
            clon = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clon;
    }
}
