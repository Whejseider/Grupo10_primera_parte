package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelo.interfaces.IAbonado;

public class VentanaPrincipal implements InterfazVista, ChangeListener {

    private JFrame frame;
    private JPanel panelAbonados;
    private JScrollPane panelTablaAbonados;
    private JTable tablaAbonados;
    private ActionListener actionListener;
    
    private DialogoNuevoAbonado dialogoNuevoAbonado;
    private ModeloTablaAbonados modeloTablaAbonados;
    private JPanel panel;
    private JButton botonBorrarAbonado;
    private JButton botonNuevoAbonado;
    
    public void setActionListener(ActionListener listener) {
        this.botonNuevoAbonado.addActionListener(listener);
        this.botonBorrarAbonado.addActionListener(listener);
        this.actionListener = listener;
    }

    /**
     * Create the application.
     */
    public VentanaPrincipal() {
        initialize();
        dialogoNuevoAbonado = new DialogoNuevoAbonado(this.frame);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 939, 480);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        this.panelAbonados = new JPanel();
        this.frame.getContentPane().add(this.panelAbonados, BorderLayout.WEST);
        
        this.modeloTablaAbonados = new ModeloTablaAbonados();
        this.tablaAbonados = new JTable(modeloTablaAbonados);
        this.tablaAbonados.setFillsViewportHeight(true);
        this.tablaAbonados.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(0).setMinWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(1).setMinWidth(40);
        this.panelAbonados.setLayout(new BorderLayout(0, 0));
        
        this.panelTablaAbonados = new JScrollPane();
        this.panelTablaAbonados.setViewportView(tablaAbonados);
        this.panelAbonados.add(panelTablaAbonados, BorderLayout.CENTER);
        
        this.panel = new JPanel();
        this.panelAbonados.add(this.panel, BorderLayout.WEST);
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        
        this.botonBorrarAbonado = new JButton("Borrar abonado");
        this.botonBorrarAbonado.setActionCommand(InterfazVista.BORRAR_ABONADO);
        this.panel.add(this.botonBorrarAbonado);
        
        this.botonNuevoAbonado = new JButton("Nuevo Abonado");
        this.botonNuevoAbonado.setActionCommand(InterfazVista.NUEVO_ABONADO);
        this.panel.add(this.botonNuevoAbonado);
        this.frame.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public NuevoAbonadoDTO pedirNuevoAbonado() {
        return this.dialogoNuevoAbonado.pedirNuevoAbonado();
    }
    
    @Override
    public void mostrarAlertaAbonadoYaExiste() {
        this.dialogoNuevoAbonado.mostrarAlertaAbonadoYaExiste();
    }
    
    @Override
    public boolean confirmarBorrarAbonado() {
        String dni = this.obtenerAbonadoSeleccionado();
        int result = JOptionPane.showConfirmDialog(this.frame, "Está seguro que desea borrar el abonado con dni " + dni + "?");
        
        if (result == JOptionPane.OK_OPTION) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 
     * @return El Dni del abonado seleccionado, o null si no hay selección.
     */
    @Override
    public String obtenerAbonadoSeleccionado() {
        int fila = this.tablaAbonados.getSelectedRow();
        if (fila == -1) return null;
        
        return (String) this.tablaAbonados.getValueAt(fila, 1);
    }
    
    /**
     * Actualiza la tabla de abonados con datos nuevos
     * 
     * @param abonados Los abonados a utilizar
     */
    @Override
    public void actualizarTablaAbonados(List<IAbonado> abonados) {
        if (abonados.size() == 0) {
            this.botonBorrarAbonado.setEnabled(false);
        } else {
            this.botonBorrarAbonado.setEnabled(true); 
        }
        this.modeloTablaAbonados.actualizar(abonados);
    }
}
