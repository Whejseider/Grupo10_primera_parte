package modelo;

import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;

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

    @Override
    public void pagarFactura(IFactura factura) {
        assert (factura != null);
        factura.setPagada(true);
    }

    @Override
    public void contratarNuevoServicio(IContrato contrato) {

        this.agregaContrato(contrato);
    }

    @Override
    public void bajaDeServicio(IContrato contrato) {
        assert (contrato != null);
        this.eliminaContrato(contrato);
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
