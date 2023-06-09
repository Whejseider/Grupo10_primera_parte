package vista.abonados;

import java.util.List;

import javax.swing.JTable;

import modelo.interfaces.IAbonado;

public class TablaAbonados extends JTable {
    public TablaAbonados() {
        super(new ModeloTablaAbonados());
        this.setFillsViewportHeight(true);
        this.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.getColumnModel().getColumn(0).setMinWidth(40);
        this.getColumnModel().getColumn(1).setMinWidth(40);
    }
    
    @Override
    public ModeloTablaAbonados getModel() {
        return this.getModel();
    }

    public void actualizar(List<IAbonado> abonados) {
        assert abonados != null;

        this.getModel().actualizar(abonados);
    }
}
