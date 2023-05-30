package modelo;

import modelo.interfaces.IPromocion;

/**
 * Subclase para abonados de tipo físico.
 */
public class AbonadoFisico extends Abonado {

    /**
     * Constructor de la clase AbonadoFisico. Crea un nuevo abonado físico.
     * El contrato es igual al especificado en la clase padre.
     */
    public AbonadoFisico(String nombre, String dni) {
        super(nombre, dni);
    }

    @Override
    public double getPagoMedioDePago(IPromocion promo) {
        return this.getPagoNeto(promo);
    }

}
