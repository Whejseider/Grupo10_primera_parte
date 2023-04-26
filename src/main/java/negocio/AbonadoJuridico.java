package negocio;

import negocio.interfaces.IAbonado;
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
    
    @Override
    public IAbonado clone() throws CloneNotSupportedException {
    	throw new CloneNotSupportedException("No se puede clonar un abonado de tipo jurídico");
    }
}
