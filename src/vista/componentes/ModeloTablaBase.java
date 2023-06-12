package vista.componentes;

import javax.swing.table.DefaultTableModel;

/**
 * Modelo de tabla que hereda de DefaultTableModel. Usado para
 * crear tablas fácilmente.
 */
public abstract class ModeloTablaBase extends DefaultTableModel {
    private final String[] columnas;

    public ModeloTablaBase(String[] columnas) {
        this.columnas = columnas;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }
}
