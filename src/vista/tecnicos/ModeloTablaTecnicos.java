package vista.tecnicos;

import modelo.tecnicos.Tecnico;
import vista.ModeloTablaBase;

import java.util.List;

public class ModeloTablaTecnicos extends ModeloTablaBase {

    public ModeloTablaTecnicos() {
        super(new String[]{"ID", "Nombre"});
    }

    /**
     * Actualiza la tabla de tecnicos con datos nuevos
     * 
     * @param tecnicos Los tecnicos a utilizar
     */
    public void actualizar(List<Tecnico> tecnicos) {
        assert tecnicos != null;

        this.setRowCount(0);
        for (Tecnico tecnico : tecnicos) {
            this.addRow(new Object[]{tecnico.getId(), tecnico.getNombre()});
        }
    }
}