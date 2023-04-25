package negocio.abonado;

import negocio.interfaces.IPromocion;

public class AbonadoJuridico extends Abonado {

    public AbonadoJuridico(String nombre, String dni) {
        super(nombre, dni);
    }

    @Override
    public double getPagoMedioDePago(IPromocion promo) {
        if (this.cantidadDeContratos() >= 3)
            return getPagoNeto(promo) * 0.5;
        return getPagoNeto(promo);
    }

}
