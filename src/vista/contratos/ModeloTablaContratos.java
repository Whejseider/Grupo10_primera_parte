package vista.contratos;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.interfaces.IContrato;

public class ModeloTablaContratos extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnNames = {"ID", "Domicilio", "Camaras", "Botones", "Tiene movil"};
    
    @Override
    public boolean isCellEditable(int row, int column){  
        return false;  
    }
    
    /**
     * Actualiza la tabla de contratos con datos nuevos
     * 
     * @param contratos Los contratos a utilizar
     */
    public void actualizar(List<IContrato> contratos) {
        assert contratos != null;
                
        this.setRowCount(0);
        for (int i = 0; i < contratos.size(); i++) {
            IContrato contrato = contratos.get(i);
            
            String identificador = Integer.toString(contrato.getIdentificador());
            String domicilio = contrato.getDomicilio();
            int camaras = contrato.getCantCamaras();
            int botones = contrato.getCantBotones();
            boolean movil = contrato.getTieneMovil();
            
            Object[] row = {identificador, domicilio, camaras, botones, movil ? "Si" : "No"};
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
