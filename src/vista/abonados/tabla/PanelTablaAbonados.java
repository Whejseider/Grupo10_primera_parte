package vista.abonados.tabla;

import modelo.interfaces.IAbonado;
import vista.InterfazVistaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelTablaAbonados extends JPanel {
    private JScrollPane panelTablaAbonados;
    private JTable tablaAbonados;
    private JLabel lblNewLabel;
    private JButton botonNuevoAbonado;
    private ActionListener actionListener;
    private DialogoNuevoAbonado dialogoNuevoAbonado;

    public void setActionListener(ActionListener listener) {
        this.botonNuevoAbonado.addActionListener(listener);
        this.actionListener = listener;
    }

    private void inicializarComandos() {
        this.botonNuevoAbonado.setActionCommand(InterfazVistaPrincipal.NUEVO_ABONADO);
    }

    public NuevoAbonadoDTO pedirNuevoAbonado() {
        return this.dialogoNuevoAbonado.pedirNuevoAbonado();
    }

    /**
     * Muestra un alert indicando que ya existe un abonado con el mismo DNI
     */
    public void mostrarAlertaAbonadoYaExiste() {
        this.dialogoNuevoAbonado.mostrarAlertaAbonadoYaExiste();
    }

    /**
     * @return El Dni del abonado seleccionado, o null si no hay selección.
     */
    public String obtenerAbonadoSeleccionado() {
        int fila = this.tablaAbonados.getSelectedRow();
        if (fila == -1) return null;

        return (String) this.tablaAbonados.getValueAt(fila, 1);
    }

    private ModeloTablaAbonados getModeloTablaAbonados() {
        return (ModeloTablaAbonados) this.tablaAbonados.getModel();
    }


    public void actualizar(List<IAbonado> abonados) {
        getModeloTablaAbonados().actualizar(abonados);
    }

    public PanelTablaAbonados(JFrame ownerFrame) {
        super();
        this.setBorder(new EmptyBorder(16, 16, 16, 16));
        this.setLayout(new BorderLayout(0, 0));

        this.tablaAbonados = new JTable(new ModeloTablaAbonados());
        this.tablaAbonados.setFillsViewportHeight(true);
        this.tablaAbonados.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(0).setMinWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(1).setMinWidth(40);
        this.tablaAbonados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actionListener.actionPerformed(new ActionEvent(tablaAbonados, 0, InterfazVistaPrincipal.SELECCION_ABONADO));
            }
        });

        this.panelTablaAbonados = new JScrollPane();
        this.panelTablaAbonados.setViewportView(tablaAbonados);
        this.add(panelTablaAbonados, BorderLayout.CENTER);

        this.lblNewLabel = new JLabel("ABONADOS");
        this.add(this.lblNewLabel, BorderLayout.NORTH);

        this.botonNuevoAbonado = new JButton("Nuevo Abonado");
        this.add(this.botonNuevoAbonado, BorderLayout.SOUTH);

        this.inicializarComandos();
        this.dialogoNuevoAbonado = new DialogoNuevoAbonado(ownerFrame);
    }

}
