package vista.tecnicos;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.interfaces.IContrato;

public class ModeloTablaTecnicos extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    private final String[] columnNames = {"ID", "Nombre", "Ocupado"};
    
    @Override
    public boolean isCellEditable(int row, int column){  
        return false;  
    }
    
    /**
     * Actualiza la tabla de tecnicos con datos nuevos
     * 
     * @param tecnicos Los tecnicos a utilizar
     */
    public void actualizar(String tecnicos) {
        //TODO implementar
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