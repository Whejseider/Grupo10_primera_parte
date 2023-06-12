package vista.abonados.detalle.contratos;

import modelo.interfaces.IContrato;
import vista.InterfazVistaPrincipal;
import vista.abonados.EstadoDialogoAlerta;
import vista.abonados.detalle.facturas.DialogoFacturaPagada;
import vista.abonados.detalle.servicio.PanelServicioTecnico;
import vista.abonados.tabla.PanelTablaAbonados;
import vista.sistema.PanelAccionesSistema;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelContratos extends JPanel {
    private ActionListener actionListener;
    private JScrollPane panelTablaContratos;
    private JTable tablaContratos;
    private JButton botonNuevoContrato;
    private JButton botonBorrarContrato;

    public void setActionListener(ActionListener listener) {
        this.botonNuevoContrato.addActionListener(listener);
        this.botonBorrarContrato.addActionListener(listener);
        this.actionListener = listener;
    }

    private void inicializarComandos() {
        this.botonNuevoContrato.setActionCommand(InterfazVistaPrincipal.NUEVO_CONTRATO);
        this.botonBorrarContrato.setActionCommand(InterfazVistaPrincipal.BORRAR_CONTRATO);
    }

    private ModeloTablaContratos getModeloTablaContratos() {
        return (ModeloTablaContratos) this.tablaContratos.getModel();
    }

    public void actualizar(List<IContrato> contratos) {
        this.getModeloTablaContratos().actualizar(contratos);
    }

    public String obtenerContratoSeleccionado() {
        int fila = this.tablaContratos.getSelectedRow();
        if (fila == -1) return null;

        return (String) this.tablaContratos.getValueAt(fila, 1);
    }

    public PanelContratos() {
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
