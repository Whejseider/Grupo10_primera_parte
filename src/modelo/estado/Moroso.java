package modelo.estado;

import modelo.AbonadoFisico;
import modelo.Factura;
import modelo.decorators.FacturaMorosoRecargoDecorator;
import modelo.interfaces.*;

import java.util.Iterator;
import java.util.List;

public class Moroso implements IEstadoFisico {
    private AbonadoFisico abonado;

    public Moroso(AbonadoFisico abonado) {
        this.abonado = abonado;
    }

    public Moroso() {
    }

    /**
     * Paga una factura y setea en true la variable Pagada<br>
     * Reemplaza la factura original por la misma pero aplicado un patron decorator para el recargo adicional<br>
     * Si la cantidad de facturas sin pagar seguidas es menor a 2 pasa al estado ConContrataciones<br>
     * (Como es moroso necesariamente tiene 2 (o mas) facturas sin pagar)
     *
     * @param factura
     */
    @Override
    public void pagarFactura(IFactura factura) {
        assert (!factura.isPagada());

        IFactura facturaDecorada = new FacturaMorosoRecargoDecorator(factura);
        List<IFactura> facturas = this.abonado.getFacturas();
        int i = facturas.indexOf(factura);

        if (i != -1)
            facturas.set(i, facturaDecorada);

        factura.setPagada(true);
        int cantFactSinPagarSeg = this.abonado.cantidadFacturasSinPagarSeguidas();
        if (cantFactSinPagarSeg < 2) {
            this.abonado.setEstado(new ConContrataciones(this.abonado));
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

    public void setAbonado(AbonadoFisico abonado) {
        this.abonado = abonado;
    }

    @Override
    public String toString() {
        return "Moroso";
    }
}
