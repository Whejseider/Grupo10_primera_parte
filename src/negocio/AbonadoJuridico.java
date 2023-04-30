package negocio;

import negocio.interfaces.IAbonado;
import negocio.interfaces.IPromocion;

/**
 * Subclase para abonados de tipo jurídico.
 */
public class AbonadoJuridico extends Abonado {

	/**
	 * Constructor de la clase AbonadoJurídico. Crea un nuevo abonado jurídico. 
	 * El contrato es igual al especificado en la clase padre.
	 */
    public AbonadoJuridico(String nombre, String dni) {
        super(nombre, dni);
    }

    @Override
    public double getPagoMedioDePago(IPromocion promo) {
        if (this.cantidadDeContratos() >= 3)
            return getPagoNeto(promo) * 0.5;
        return getPagoNeto(promo);
    }
    
    /**
     * Método para clonar el abonado. 
     * Siempre tira excepcion al no ser posible clonar abonados de tipo jurídico.
     */
    @Override
    public IAbonado clone() throws CloneNotSupportedException {
    	throw new CloneNotSupportedException("No se puede clonar un abonado de tipo jurídico");
    }
}
