package vista.abonados;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.interfaces.IAbonado;

public class ModeloTablaAbonados extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnNames = {"Nombre", "DNI"};
    
    @Override
    public boolean isCellEditable(int row, int column){  
        return false;  
    }
    
    /**
     * Actualiza la tabla de abonados con datos nuevos
     * 
     * @param abonados Los abonados a utilizar
     */
    public void actualizar(List<IAbonado> abonados) {
        assert abonados != null;
                
        this.setRowCount(0);
        for (int i = 0; i < abonados.size(); i++) {
            IAbonado abonado = abonados.get(i);
            
            String[] row = {abonado.getNombre(), abonado.getDni()};
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
