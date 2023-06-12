package vista.abonados.detalle.contratos;

import modelo.interfaces.IContrato;
import vista.InterfazVistaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelContratos extends JPanel {
    private final JFrame ownerFrame;
    private ActionListener actionListener;
    private final JScrollPane panelTablaContratos;
    private final JTable tablaContratos;
    private final JButton botonNuevoContrato;
    private JButton botonBorrarContrato;
    private DialogoNuevoContrato dialogoNuevoContrato;

    public void setActionListener(ActionListener listener) {
        this.botonNuevoContrato.addActionListener(listener);
        this.botonBorrarContrato.addActionListener(listener);
        this.actionListener = listener;
    }

    private void inicializarComandos() {
        this.botonNuevoContrato.setActionCommand(InterfazVistaPrincipal.NUEVO_CONTRATO);
        this.botonBorrarContrato.setActionCommand(InterfazVistaPrincipal.BORRAR_CONTRATO);
    }

    public void actualizar(List<IContrato> contratos) {
        this.getModeloTablaContratos().actualizar(contratos);
    }

    private ModeloTablaContratos getModeloTablaContratos() {
        return (ModeloTablaContratos) this.tablaContratos.getModel();
    }

    public String obtenerContratoSeleccionado() {
        int fila = this.tablaContratos.getSelectedRow();
        if (fila == -1) return null;

        return (String) this.tablaContratos.getValueAt(fila, 1);
    }

    public boolean confirmarBorrarContrato() {
        String domicilio = this.obtenerContratoSeleccionado();
        int result = JOptionPane.showConfirmDialog(
                this.ownerFrame,
                "Está seguro que desea eliminar el contrato con domicilio " + domicilio + "?"
        );

        return result == JOptionPane.OK_OPTION;
    }

    public void mostrarAlertaDomicilioDuplicado() {
        this.dialogoNuevoContrato.mostrarAlertaDomicilioDuplicado();
    }

    public NuevoContratoDTO pedirNuevoContrato() {
        return this.dialogoNuevoContrato.pedirNuevoContrato();
    }

    public PanelContratos(JFrame ownerFrame) {
        this.ownerFrame = ownerFrame;
        this.dialogoNuevoContrato = new DialogoNuevoContrato(ownerFrame);

        this.setLayout(new BorderLayout(0, 0));

        this.panelTablaContratos = new JScrollPane();
        this.add(this.panelTablaContratos, BorderLayout.CENTER);

        this.tablaContratos = new JTable(new ModeloTablaContratos());
        this.tablaContratos.setFillsViewportHeight(true);

        this.tablaContratos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                botonBorrarContrato.setEnabled(tablaContratos.getSelectedRow() > -1);
                actionListener.actionPerformed(new ActionEvent(tablaContratos, 0, InterfazVistaPrincipal.SELECCION_CONTRATO));
            }
        });

        this.panelTablaContratos.setViewportView(this.tablaContratos);

        this.botonNuevoContrato = new JButton("Nuevo Contrato");
        this.botonNuevoContrato.setActionCommand(InterfazVistaPrincipal.NUEVO_CONTRATO);
        this.add(this.botonNuevoContrato, BorderLayout.SOUTH);

        this.botonBorrarContrato = new JButton("Borrar contrato");
        this.botonBorrarContrato.setActionCommand(InterfazVistaPrincipal.BORRAR_CONTRATO);
        this.botonBorrarContrato.setEnabled(false);
        this.add(this.botonBorrarContrato, BorderLayout.NORTH);

        inicializarComandos();
    }
}
