package vista.facturas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.interfaces.IFactura;

public class ModeloTablaFacturas extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnNames = {"Fecha", "Subtotal", "Total"};
    
    @Override
    public boolean isCellEditable(int row, int column){  
        return false;  
    }
    
    /**
     * Actualiza la tabla de facturas con datos nuevos
     * 
     * @param contratos Las facturas a utilizar
     */
    public void actualizar(List<IFactura> facturas) {
        assert facturas != null;
        
        this.setRowCount(0);
        for (int i = 0; i < facturas.size(); i++) {
            IFactura factura = facturas.get(i);
            
            String fecha = "Junio 2023";
            double subtotal = factura.getSubtotal();
            double total = factura.getValorNeto();
            
            Object[] row = {fecha, "$" + subtotal, "$" + BigDecimal.valueOf(total)
            .setScale(3, RoundingMode.HALF_UP)
            .doubleValue()};
            this.addRow(row);
        }
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
}