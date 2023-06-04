package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.AbonadoJuridico;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import vista.abonados.DialogoNuevoAbonado;
import vista.abonados.ModeloTablaAbonados;
import vista.abonados.NuevoAbonadoDTO;
import vista.contratos.DialogoNuevoContrato;
import vista.contratos.ModeloTablaContratos;
import vista.contratos.NuevoContratoDTO;

public class VentanaPrincipal implements InterfazVista, ChangeListener {

    private JFrame frame;
    private JPanel panelAbonados;
    private JScrollPane panelTablaAbonados;
    private JTable tablaAbonados;
    private ActionListener actionListener;
    
    private DialogoNuevoAbonado dialogoNuevoAbonado;
    private DialogoNuevoContrato dialogoNuevoContrato;
    
    private JPanel panel;
    private JPanel panelContratos;
    private JLabel lblNewLabel;
    private JButton botonNuevoAbonado;
    private JPanel panel_1;
    private JButton botonBorrarAbonado;
    private JButton btnNewButton;
    private JPanel panel_2;
    private JLabel labelNombreAbonado;
    private JLabel labelDniAbonado;
    private JPanel panel_3;
    private JScrollPane panelTablaContratos;
    private JTable tablaContratos;
    private JLabel tituloContratos;
    private JButton botonNuevoContrato;
    private JLabel labelTipoAbonado;
    private JLabel labelEstadoAbonado;
    private JButton btnNewButton_2;
    
    public void setActionListener(ActionListener listener) {
        this.botonNuevoAbonado.addActionListener(listener);
        this.botonBorrarAbonado.addActionListener(listener);
        this.botonNuevoContrato.addActionListener(listener);
        this.actionListener = listener;
    }

    /**
     * Create the application.
     */
    public VentanaPrincipal() {
        initialize();
        dialogoNuevoAbonado = new DialogoNuevoAbonado(this.frame);
        dialogoNuevoContrato = new DialogoNuevoContrato(this.frame);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 928, 656);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
        
        this.panelAbonados = new JPanel();
        this.panelAbonados.setBorder(new EmptyBorder(16, 16, 16, 16));
        this.frame.getContentPane().add(this.panelAbonados);
        
        this.tablaAbonados = new JTable(new ModeloTablaAbonados());
        this.tablaAbonados.setFillsViewportHeight(true);
        this.tablaAbonados.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(0).setMinWidth(40);
        this.tablaAbonados.getColumnModel().getColumn(1).setMinWidth(40);
        this.tablaAbonados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actionListener.actionPerformed(new ActionEvent(tablaAbonados, 0, InterfazVista.SELECCION_ABONADO));
            }
        });

        this.panelAbonados.setLayout(new BorderLayout(0, 0));
        
        this.panelTablaAbonados = new JScrollPane();
        this.panelTablaAbonados.setViewportView(tablaAbonados);
        this.panelAbonados.add(panelTablaAbonados, BorderLayout.CENTER);
        
        this.panel = new JPanel();
        this.panelAbonados.add(this.panel, BorderLayout.WEST);
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        
        this.lblNewLabel = new JLabel("ABONADOS");
        this.panelAbonados.add(this.lblNewLabel, BorderLayout.NORTH);
        
        this.botonNuevoAbonado = new JButton("Nuevo Abonado");
        this.botonNuevoAbonado.setActionCommand(InterfazVista.NUEVO_ABONADO);
        this.panelAbonados.add(this.botonNuevoAbonado, BorderLayout.SOUTH);
        
        this.panelContratos = new JPanel();
        this.panelContratos.setBorder(new EmptyBorder(24, 16, 16, 16));
        this.frame.getContentPane().add(this.panelContratos);
        this.panelContratos.setLayout(new BoxLayout(this.panelContratos, BoxLayout.Y_AXIS));
        this.panelContratos.setVisible(false);
        
        this.panel_1 = new JPanel();
        this.panel_1.setBorder(new EmptyBorder(0, 0, 4, 0));
        this.panelContratos.add(this.panel_1);
        this.panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        this.botonBorrarAbonado = new JButton("Borrar abonado");
        this.botonBorrarAbonado.setActionCommand(InterfazVista.BORRAR_ABONADO);
        this.panel_1.add(this.botonBorrarAbonado);
        
        this.btnNewButton = new JButton("Ver facturas");
        this.panel_1.add(this.btnNewButton);
        
        this.btnNewButton_2 = new JButton("Pagar");
        this.panel_1.add(this.btnNewButton_2);
        
        this.panel_2 = new JPanel();
        this.panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel_2.setBorder(new EmptyBorder(8, 8, 8, 8));
        this.panelContratos.add(this.panel_2);
        this.panel_2.setLayout(new BoxLayout(this.panel_2, BoxLayout.Y_AXIS));
        
        this.labelNombreAbonado = new JLabel("Nombre: Bautista");
        this.panel_2.add(this.labelNombreAbonado);
        
        this.labelDniAbonado = new JLabel("DNI: 12412");
        this.panel_2.add(this.labelDniAbonado);
        
        this.labelTipoAbonado = new JLabel("Tipo: JURIDICO");
        this.panel_2.add(this.labelTipoAbonado);
        
        this.labelEstadoAbonado = new JLabel("Estado: MOROSO");
        this.panel_2.add(this.labelEstadoAbonado);
        
        this.panel_3 = new JPanel();
        this.panelContratos.add(this.panel_3);
        this.panel_3.setLayout(new BorderLayout(0, 0));
        
        this.panelTablaContratos = new JScrollPane();
        this.panel_3.add(this.panelTablaContratos, BorderLayout.CENTER);
        
        this.tablaContratos = new JTable(new ModeloTablaContratos());
        this.tablaContratos.setFillsViewportHeight(true);
        this.panelTablaContratos.setViewportView(this.tablaContratos);
        
        this.tituloContratos = new JLabel("CONTRATOS");
        this.panel_3.add(this.tituloContratos, BorderLayout.NORTH);
        
        this.botonNuevoContrato = new JButton("Nuevo Contrato");
        this.botonNuevoContrato.setActionCommand(InterfazVista.NUEVO_CONTRATO);
        this.panel_3.add(this.botonNuevoContrato, BorderLayout.SOUTH);
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
    
    /**
     * Muestra un alert indicando que ya existe un abonado con el mismo DNI
     */
    @Override
    public void mostrarAlertaAbonadoYaExiste() {
        this.dialogoNuevoAbonado.mostrarAlertaAbonadoYaExiste();
    }
    
    /**
     * Muestra un alert indicando que ya existe un contrato con el mismo domicilio
     */
    @Override
    public void mostrarAlertaDomicilioDuplicado() {
        this.dialogoNuevoContrato.mostrarAlertaDomicilioDuplicado();
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
    
    private ModeloTablaAbonados getModeloTablaAbonados() {
        return (ModeloTablaAbonados) this.tablaAbonados.getModel();
    }
    
    private ModeloTablaContratos getModeloTablaContratos() {
        return (ModeloTablaContratos) this.tablaContratos.getModel();
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
        this.getModeloTablaAbonados().actualizar(abonados);
    }
    
    @Override
    public void actualizarDetallesAbonado(IAbonado abonado) {
        if (this.obtenerAbonadoSeleccionado() == null) {
            this.panelContratos.setVisible(false);
            return;
        }
        
        this.panelContratos.setVisible(true);
        this.actualizarTablaContratos(abonado.getContratos());
        this.labelDniAbonado.setText("Dni: " + abonado.getDni());
        this.labelNombreAbonado.setText("Nombre: " + abonado.getNombre());
        //TODO: Implementar funciones de getIsFisico y demas a abonado, esto esta mal
        this.labelTipoAbonado.setText("Tipo: " + (abonado instanceof AbonadoJuridico ? "Juridico" : "Fisico"));
        
    }
    @Override
    public NuevoContratoDTO pedirNuevoContrato() {
        return this.dialogoNuevoContrato.pedirNuevoContrato();
    }
    /**
     * Actualiza la tabla de contratos con datos nuevos
     * 
     * @param contratos Los contratos a utilizar
     */
    @Override
    public void actualizarTablaContratos(List<IContrato> contratos) {
        this.getModeloTablaContratos().actualizar(contratos);
    }
}
