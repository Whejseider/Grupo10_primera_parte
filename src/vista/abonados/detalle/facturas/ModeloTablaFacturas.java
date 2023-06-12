package vista.abonados.detalle.facturas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import modelo.interfaces.IFactura;
import vista.componentes.ModeloTablaBase;

public class ModeloTablaFacturas extends ModeloTablaBase {
    public ModeloTablaFacturas() {
        super(new String[]{"ID", "Fecha", "Subtotal", "Total", "Pagada"});
    }

    /**
     * Actualiza la tabla de facturas con datos nuevos
     * 
     * @param facturas Las facturas a utilizar
     */
    public void actualizar(List<IFactura> facturas) {
        assert facturas != null;
        
        this.setRowCount(0);
        for (int i = 0; i < facturas.size(); i++) {
            IFactura factura = facturas.get(i);

            LocalDate fecha = factura.getFecha();
            double subtotal = factura.getSubtotal();
            double total = factura.getValorNeto();

            Object[] row = {factura.getId(), fecha.toString(), "$" + subtotal, "$" + BigDecimal.valueOf(total)
                    .setScale(4, RoundingMode.HALF_UP)
                    .doubleValue(), factura.isPagada() ? "Si" : "No"};

            this.addRow(row);
        }
    }
}