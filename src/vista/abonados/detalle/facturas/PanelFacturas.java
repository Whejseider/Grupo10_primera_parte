package vista.abonados.detalle.facturas;

import modelo.interfaces.IFactura;
import vista.InterfazVistaPrincipal;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class PanelFacturas extends JPanel {
    private final JFrame ownerFrame;
    private final JScrollPane panelTablaFacturas;
    private final JTable tablaFacturas;
    private ActionListener actionListener;

    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    private ModeloTablaFacturas getModeloTablaFacturas() {
        return (ModeloTablaFacturas) this.tablaFacturas.getModel();
    }

    public Integer obtenerFacturaSeleccionado() {
        int fila = this.tablaFacturas.getSelectedRow();
        if (fila == -1) return -1;

        return (Integer) this.tablaFacturas.getValueAt(fila, 0);
    }

    public void actualizar(List<IFactura> facturas) {
        this.getModeloTablaFacturas().actualizar(facturas);
    }

    public boolean confirmarPagarFactura() {
        Integer id = this.obtenerFacturaSeleccionado();
        int result = JOptionPane.showConfirmDialog(
                this.ownerFrame,
                "Está seguro que desea pagar el contrato con id " + id + "?"
        );

        return result == JOptionPane.OK_OPTION;
    }

    public void mostrarDialogoFactura(IFactura factura) {
        JTextArea textarea = new JTextArea();
        textarea.setText(factura.getDetalle());
        JOptionPane.showMessageDialog(this.ownerFrame, textarea);
    }

    public PanelFacturas(JFrame ownerFrame) {
        super();
        this.ownerFrame = ownerFrame;
        this.setLayout(new BorderLayout(0, 0));

        this.panelTablaFacturas = new JScrollPane();
        this.add(this.panelTablaFacturas);

        this.tablaFacturas = new JTable(new ModeloTablaFacturas());
        this.tablaFacturas.setFillsViewportHeight(true);

        this.tablaFacturas.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tablaFacturas.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) tablaFacturas.getValueAt(selectedRow, 0);
                        ActionEvent event = new ActionEvent(id, 0, InterfazVistaPrincipal.MOSTRAR_FACTURA);
                        actionListener.actionPerformed(event);
                    }
                }
            }
        });

        this.panelTablaFacturas.setViewportView(this.tablaFacturas);
    }
}
