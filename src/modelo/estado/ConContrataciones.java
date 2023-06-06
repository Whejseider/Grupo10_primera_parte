package modelo.estado;

import modelo.AbonadoFisico;
import modelo.interfaces.*;

import java.util.Iterator;

public class ConContrataciones implements IEstadoFisico {
    private AbonadoFisico abonado;

    public ConContrataciones(AbonadoFisico abonado) {
        this.abonado = abonado;
    }


    /**
     * Paga una factura y setea la variable Pagada a true
     * @param factura factura
     */
    @Override
    public void pagarFactura(IFactura factura) {
        assert (!factura.isPagada());

        factura.setPagada(true);
    }

    /**
     * Contrata un servico y si tiene 2 facturas sin pagar seguidas actualiza el estado a Moroso
     * @param contrato contrato
     */
    @Override
    public void contratarNuevoServicio(IContrato contrato) {
        assert (contrato != null);

        this.abonado.agregaContrato(contrato);
        if (this.abonado.cantidadFacturasSinPagarSeguidas() >= 2) {
            this.abonado.setEstado(new Moroso(this.abonado));
            System.out.println("Ahora el estado es Moroso"); //Test
        }
    }

    /**
     * Da de baja un servicio eliminando el contrato, si no tiene ningun contrato pasa al estado SinContratacion
     * @param contrato contrato
     */
    @Override
    public void bajaDeServicio(IContrato contrato) {
        assert (contrato != null);

        this.abonado.eliminaContrato(contrato);
        System.out.println("Se ha dado de baja el contrato");
        if (this.abonado.getContratos().size() == 0) {
            this.abonado.setEstado(new SinContratacion(this.abonado));
            System.out.println("Ahora el estado es Sin Contratacion"); //Test
        }
    }

    public AbonadoFisico getAbonado() {
        return abonado;
    }

    @Override
    public String toString() {
        return "ConContrataciones{" +
                "abonado=" + abonado +
                '}';
    }
}
