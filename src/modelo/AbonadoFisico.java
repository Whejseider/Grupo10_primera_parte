package modelo;

import modelo.estado.ConContrataciones;
import modelo.estado.SinContratacion;
import modelo.interfaces.IContrato;
import modelo.interfaces.IEstadoFisico;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;

/**
 * Subclase para abonados de tipo físico.
 */
public class AbonadoFisico extends Abonado {
    private IEstadoFisico estado;

    /**
     * Constructor de la clase AbonadoFisico. Crea un nuevo abonado físico.
     * El contrato es igual al especificado en la clase padre.
     */
    public AbonadoFisico() {

    }

    public AbonadoFisico(String nombre, String dni) {
        super(nombre, dni);
        this.estado = new SinContratacion(this);
    }

    @Override
    public double getPagoMedioDePago(IPromocion promo) {
        return this.getPagoNeto(promo);
    }

    public IEstadoFisico getEstado() {
        return estado;
    }

    public void setEstado(IEstadoFisico estado) {
        this.estado = estado;
    }

    @Override
    public void pagarFactura(IFactura factura) {
        this.estado.pagarFactura(factura);
    }

    @Override
    public void contratarNuevoServicio(IContrato contrato) {
        this.estado.contratarNuevoServicio(contrato);
    }

    @Override
    public void bajaDeServicio(IContrato contrato) {
        this.estado.bajaDeServicio(contrato);
    }

    @Override
    public boolean isFisico() {
        return true;
    }
}
