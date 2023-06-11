package modelo.estado;

import modelo.AbonadoFisico;
import modelo.interfaces.*;

public class SinContratacion implements IEstadoFisico {
    private AbonadoFisico abonado;

    public SinContratacion() {

    }

    public SinContratacion(AbonadoFisico abonado) {
        this.abonado = abonado;
    }

    @Override
    public void pagarFactura(IFactura factura) {
        // TO DO
        throw new UnsupportedOperationException("Operacion no permitida"); // Test
    }

    /**
     * Contrata un nuevo servicio para el abonado y cambia su estado a
     * ConContrataciones
     * 
     * @param contrato contrato del servicio
     */
    @Override
    public void contratarNuevoServicio(IContrato contrato) {
        assert (contrato != null);

        this.abonado.agregaContrato(contrato);
        this.abonado.setEstado(new ConContrataciones(this.abonado));
        System.out.println("Ahora el estado es ConContrataciones"); // Test
    }

    @Override
    public void bajaDeServicio(IContrato contrato) {
        // TO DO
        throw new UnsupportedOperationException("Operacion no permitida"); // Test

    }

    public void setAbonado(AbonadoFisico abonado) {
        this.abonado = abonado;
    }

    public AbonadoFisico getAbonado() {
        return abonado;
    }

    @Override
    public String toString() {
        return "Sin contratación";
    }
}
