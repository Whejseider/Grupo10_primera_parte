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

}
