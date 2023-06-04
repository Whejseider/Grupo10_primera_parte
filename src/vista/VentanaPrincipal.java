package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelo.interfaces.IAbonado;

public class VentanaPrincipal implements InterfazVista, MouseListener, ChangeListener {

    private JFrame frame;
    private JPanel panelAbonados;
    private JButton botonNuevoAbonado;
    private JScrollPane panelTablaAbonados;
    private JTable tablaAbonados;
    private ActionListener actionListener;
    
    private DialogoNuevoAbonado dialogoNuevoAbonado;
    private ModeloTablaAbonados modeloTablaAbonados;
    private JScrollPane scrollPane_1;
    
    public void setActionListener(ActionListener listener) {
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
        this.panelAbonados.setLayout(new BorderLayout(0, 0));
        
        this.modeloTablaAbonados = new ModeloTablaAbonados();
        this.tablaAbonados = new JTable(modeloTablaAbonados);
        this.tablaAbonados.setFillsViewportHeight(true);
        this.tablaAbonados.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(0).setMinWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(1).setMinWidth(40);
        
        this.panelTablaAbonados = new JScrollPane();
        this.panelTablaAbonados.setViewportView(tablaAbonados);
        this.panelAbonados.add(panelTablaAbonados, BorderLayout.CENTER);
        
        this.botonNuevoAbonado = new JButton("Nuevo Abonado");
        this.botonNuevoAbonado.addMouseListener(this);
        this.panelAbonados.add(this.botonNuevoAbonado, BorderLayout.SOUTH);
        this.frame.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        this.actionListener.actionPerformed(new ActionEvent(this, 0, InterfazVista.NUEVO_ABONADO));
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
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
    
    /**
     * Actualiza la tabla de abonados con datos nuevos
     * 
     * @param abonados Los abonados a utilizar
     */
    @Override
    public void actualizarTablaAbonados(List<IAbonado> abonados) {
        this.modeloTablaAbonados.actualizar(abonados);
    }
}
