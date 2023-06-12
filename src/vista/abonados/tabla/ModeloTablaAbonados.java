package vista.abonados.tabla;

import modelo.interfaces.IAbonado;
import vista.componentes.ModeloTablaBase;

import java.util.List;

public class ModeloTablaAbonados extends ModeloTablaBase {
    private static final long serialVersionUID = 1L;

    public ModeloTablaAbonados() {
        super(new String[]{"Nombre", "DNI"});
    }

    /**
     * Actualiza la tabla de abonados con datos nuevos
     * 
     * @param abonados Los abonados a utilizar
     */
    public void actualizar(List<IAbonado> abonados) {
        assert abonados != null;
                
        this.setRowCount(0);
        for (IAbonado abonado : abonados) {
            String[] row = {abonado.getNombre(), abonado.getDni()};
            this.addRow(row);
        }
    }
}
