package modelo.estado;

import modelo.AbonadoFisico;
import modelo.interfaces.*;

public class Moroso implements IEstadoFisico {
    private AbonadoFisico abonado;

    public Moroso(AbonadoFisico abonado) {
        this.abonado = abonado;
    }

    /**
     * Paga una factura y setea en true la variable Pagada<br>
     * Si la cantidad de facturas sin pagar seguidas es menor a 2 pasa al estado ConContrataciones<br>
     * (Como es moroso necesariamente tiene 2 (o mas) facturas sin pagar)
     * @param factura
     */
    @Override
    public void pagarFactura(IFactura factura) {
        assert (!factura.isPagada());

        factura.setValorNeto(factura.getValorNeto() * 1.3);
        factura.setPagada(true);
        if (this.abonado.cantidadFacturasSinPagarSeguidas()<2) {
            this.abonado.setEstado(new ConContrataciones(this.abonado));
            System.out.println("Ahora el estado es Con Contrataciones"); //Test
        }
    }

    @Override
    public void contratarNuevoServicio(IContrato contrato) {
        throw new UnsupportedOperationException("Operacion no permitida"); //Test

    }

    @Override
    public void bajaDeServicio(IContrato contrato) {
        throw new UnsupportedOperationException("Operacion no permitida"); //Test

    }

    public AbonadoFisico getAbonado() {
        return abonado;
    }

    @Override
    public String toString() {
        return "Moroso{" +
                "abonado=" + abonado +
                '}';
    }
}
