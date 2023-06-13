package vista.tecnicos;

import modelo.tecnicos.ITecnico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaTecnicos implements InterfazVentanaTecnicos {
    private JFrame ownerFrame;
    private JDialog frame;
    private ActionListener actionListener;
    private JScrollPane panelTablaTecnicos;
    private JTable tablaTecnicos;
    private JButton botonNuevoTecnico;
    private JButton botonBorrarTecnico;

    private final DialogoNuevoTecnico dialogoNuevoTecnico;
    /**
     * Create the application.
     */
    public VentanaTecnicos(JFrame ownerFrame) {
        this.ownerFrame = ownerFrame;
        initialize(ownerFrame);
        this.dialogoNuevoTecnico = new DialogoNuevoTecnico(this.frame);
    }

    @Override
    public void setActionListener(ActionListener listener) {
        this.botonBorrarTecnico.addActionListener(listener);
        this.botonNuevoTecnico.addActionListener(listener);
        this.actionListener = listener;
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    private ModeloTablaTecnicos getModeloTablaTecnicos() {
        return (ModeloTablaTecnicos) this.tablaTecnicos.getModel();
    }

    @Override
    public void actualizar(List<ITecnico> tecnicos) {
        ModeloTablaTecnicos modeloTabla = this.getModeloTablaTecnicos();
        modeloTabla.actualizar(tecnicos);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(JFrame ownerFrame) {
        this.frame = new JDialog();
        this.frame.setSize(400, 300);
        this.frame.setLocationRelativeTo(ownerFrame);
        this.frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.frame.getContentPane().setLayout(new BorderLayout(0, 0));

        this.panelTablaTecnicos = new JScrollPane();
        this.frame.add(this.panelTablaTecnicos, BorderLayout.NORTH);

        this.tablaTecnicos = new JTable();
        this.tablaTecnicos.setModel(new ModeloTablaTecnicos());
        this.tablaTecnicos.setFillsViewportHeight(true);
        this.panelTablaTecnicos.setViewportView(this.tablaTecnicos);

        this.botonNuevoTecnico = new JButton("Nuevo tecnico");
        this.botonNuevoTecnico.setActionCommand(InterfazVentanaTecnicos.NUEVO_TECNICO);
        this.frame.getContentPane().add(this.botonNuevoTecnico, BorderLayout.CENTER);

        this.botonBorrarTecnico = new JButton("Borrar Tecnico");
        this.botonBorrarTecnico.setActionCommand(InterfazVentanaTecnicos.BORRAR_TECNICO);
        this.frame.getContentPane().add(this.botonBorrarTecnico, BorderLayout.SOUTH);

        this.frame.pack();
        this.frame.setVisible(false);
    }

    public NuevoTecnicoDTO pedirNuevoTecnico() {
        return this.dialogoNuevoTecnico.pedirNuevoTecnico();
    }

    public String obtenerTecnicoSeleccionado() {
        int fila = this.tablaTecnicos.getSelectedRow();
        if (fila == -1) return null;

        return (String) this.tablaTecnicos.getValueAt(fila, 1);
    }

    public void mostrarAlertaTecnicoYaExiste() {
        this.dialogoNuevoTecnico.mostrarAlertaTecnicoYaExiste();
    }

    public void mostrarAlertaTecnicoNoSePuedeBorrar() {
        JOptionPane.showMessageDialog(ownerFrame, "El tecnico no se puede borrar ya que tiene un service en curso.\nEspere a que termine para eliminarlo.");
    }
}
