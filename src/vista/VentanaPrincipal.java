package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;

import modelo.*;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import vista.abonados.DialogoNuevoAbonado;
import vista.abonados.ModeloTablaAbonados;
import vista.abonados.NuevoAbonadoDTO;
import vista.contratos.DialogoNuevoContrato;
import vista.contratos.ModeloTablaContratos;
import vista.contratos.NuevoContratoDTO;
import vista.facturas.ModeloTablaFacturas;

public class VentanaPrincipal implements InterfazVistaPrincipal, ChangeListener {

    private JFrame frame;
    private JPanel panelAbonados;
    private JScrollPane panelTablaAbonados;
    private JTable tablaAbonados;
    private ActionListener actionListener;
    
    private DialogoNuevoAbonado dialogoNuevoAbonado;
    private DialogoNuevoContrato dialogoNuevoContrato;
    
    private JPanel panel;
    private JPanel panelPrincipalAbonado;
    private JPanel panelAbonado;
    private JLabel lblNewLabel;
    private JButton botonNuevoAbonado;
    private JPanel panelAccionesAbonado;
    private JButton botonBorrarAbonado;
    private JPanel panelDatosAbonado;
    private JLabel labelNombreAbonado;
    private JLabel labelDniAbonado;
    private JPanel panelContratos;
    private JScrollPane panelTablaContratos;
    private JTable tablaContratos;
    private JButton botonNuevoContrato;
    private JLabel labelTipoAbonado;
    private JLabel labelEstadoAbonado;
    private JButton botonPagarEfectivo;
    private JTabbedPane tabsAbonado;
    private JPanel panelFacturas;
    private JScrollPane panelTablaFacturas;
    private JTable tablaFacturas;
    private JButton botonPagarTarjeta;
    private JButton botonPagarCheque;
    private JPanel panelAccionesSistema;
    private JPanel panelPromociones;
    private JButton botonSinPromocion;
    private JButton botonPromocionDorada;
    private JButton botonPromocionPlatino;
    private JPanel panelTecnicos;
    private JButton botonGestionarTecnicos;
    
    public void setActionListener(ActionListener listener) {
        this.botonNuevoAbonado.addActionListener(listener);
        this.botonBorrarAbonado.addActionListener(listener);
        this.botonNuevoContrato.addActionListener(listener);
        this.botonPagarTarjeta.addActionListener(listener);
        this.botonPagarCheque.addActionListener(listener);
        this.botonPagarEfectivo.addActionListener(listener);
        this.botonSinPromocion.addActionListener(listener);
        this.botonPromocionPlatino.addActionListener(listener);
        this.botonPromocionDorada.addActionListener(listener);
        this.botonGestionarTecnicos.addActionListener(listener);
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
        this.frame.setBounds(100, 100, 922, 678);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        this.panelAbonados = new JPanel();
        this.panelAbonados.setBorder(new EmptyBorder(16, 16, 16, 16));
        this.frame.getContentPane().add(this.panelAbonados, BorderLayout.WEST);
        
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
        this.botonNuevoAbonado.setActionCommand(InterfazVistaPrincipal.NUEVO_ABONADO);
        this.panelAbonados.add(this.botonNuevoAbonado, BorderLayout.SOUTH);
        
        this.panelAbonado = new JPanel();
        this.panelAbonado.setBorder(new EmptyBorder(24, 16, 16, 16));
        this.frame.getContentPane().add(this.panelAbonado, BorderLayout.CENTER);
        this.panelAbonado.setVisible(false);
        this.panelAbonado.setLayout(new BorderLayout(0, 0));
        
        this.panelAccionesAbonado = new JPanel();
        this.panelAccionesAbonado.setBorder(new EmptyBorder(0, 8, 4, 0));
        this.panelAbonado.add(this.panelAccionesAbonado, BorderLayout.EAST);
        this.panelAccionesAbonado.setLayout(new BoxLayout(this.panelAccionesAbonado, BoxLayout.Y_AXIS));
        
        this.botonBorrarAbonado = new JButton("Borrar abonado");
        this.botonBorrarAbonado.setActionCommand(InterfazVistaPrincipal.BORRAR_ABONADO);
        this.panelAccionesAbonado.add(this.botonBorrarAbonado);
        
        this.botonPagarEfectivo = new JButton("Pagar con efectivo");
        this.botonPagarEfectivo.setActionCommand(InterfazVistaPrincipal.PAGAR_FACTURA_EFECTIVO);
        this.panelAccionesAbonado.add(this.botonPagarEfectivo);
        
        this.botonPagarTarjeta = new JButton("Pagar con tarjeta");
        this.botonPagarTarjeta.setActionCommand(InterfazVistaPrincipal.PAGAR_FACTURA_TARJETA);
        this.panelAccionesAbonado.add(this.botonPagarTarjeta);
        
        this.botonPagarCheque = new JButton("Pagar con cheque");
        this.botonPagarCheque.setActionCommand(InterfazVistaPrincipal.PAGAR_FACTURA_CHEQUE);
        this.panelAccionesAbonado.add(this.botonPagarCheque);
        
        this.panelPrincipalAbonado = new JPanel();
        this.panelPrincipalAbonado.setLayout(new BorderLayout(0, 0));
        
        this.panelDatosAbonado = new JPanel();
        this.panelDatosAbonado.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panelDatosAbonado.setBorder(new EmptyBorder(8, 8, 8, 8));
        this.panelPrincipalAbonado.add(this.panelDatosAbonado, BorderLayout.NORTH);
        this.panelDatosAbonado.setLayout(new BoxLayout(this.panelDatosAbonado, BoxLayout.Y_AXIS));
        
        this.labelNombreAbonado = new JLabel("Nombre: Bautista");
        this.panelDatosAbonado.add(this.labelNombreAbonado);
        
        this.labelDniAbonado = new JLabel("DNI: 12412");
        this.panelDatosAbonado.add(this.labelDniAbonado);
        
        this.labelTipoAbonado = new JLabel("Tipo: JURIDICO");
        this.panelDatosAbonado.add(this.labelTipoAbonado);
        
        this.labelEstadoAbonado = new JLabel("Estado: MOROSO");
        this.panelDatosAbonado.add(this.labelEstadoAbonado);
        
        this.panelContratos = new JPanel();
        this.panelContratos.setLayout(new BorderLayout(0, 0));
        
        this.panelTablaContratos = new JScrollPane();
        this.panelContratos.add(this.panelTablaContratos, BorderLayout.CENTER);
        
        this.tablaContratos = new JTable(new ModeloTablaContratos());
        this.tablaContratos.setFillsViewportHeight(true);
        this.panelTablaContratos.setViewportView(this.tablaContratos);
        
        this.botonNuevoContrato = new JButton("Nuevo Contrato");
        this.botonNuevoContrato.setActionCommand(InterfazVistaPrincipal.NUEVO_CONTRATO);
        this.panelContratos.add(this.botonNuevoContrato, BorderLayout.SOUTH);
        
        this.tabsAbonado = new JTabbedPane(JTabbedPane.TOP);
        this.tabsAbonado.addTab("Contratos", this.panelContratos);
        this.panelPrincipalAbonado.add(this.tabsAbonado);
        
        this.panelFacturas = new JPanel();
        this.panelFacturas.setLayout(new BorderLayout(0, 0));
        
        
        this.panelTablaFacturas = new JScrollPane();
        this.panelFacturas.add(this.panelTablaFacturas);
        
        this.tablaFacturas = new JTable(new ModeloTablaFacturas());
        this.tablaFacturas.setFillsViewportHeight(true);
        this.tablaFacturas.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tablaFacturas.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) tablaFacturas.getValueAt(selectedRow, 0);
                        ActionEvent event = new ActionEvent(id, 0, "MOSTRAR_FACTURA");
                        actionListener.actionPerformed(event);
                    }
                }
            }
        });

        this.panelTablaFacturas.setViewportView(this.tablaFacturas);
        this.tabsAbonado.addTab("Facturas",this.panelFacturas);
        this.panelAbonado.add(this.panelPrincipalAbonado);
        
        this.panelAccionesSistema = new JPanel();
        this.panelAccionesSistema.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
        this.frame.getContentPane().add(this.panelAccionesSistema, BorderLayout.NORTH);
        
        this.panelPromociones = new JPanel();
        FlowLayout flowLayout = (FlowLayout) this.panelPromociones.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        this.panelPromociones.setBorder(new TitledBorder(null, "Promociones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.panelAccionesSistema.add(this.panelPromociones);
        
        this.botonSinPromocion = new JButton("Sin Promocion");
        this.botonSinPromocion.setActionCommand(InterfazVistaPrincipal.PROMOCION_NINGUNA);
        this.panelPromociones.add(this.botonSinPromocion);
        
        this.botonPromocionDorada = new JButton("Promocion Dorada");
        this.botonPromocionDorada.setActionCommand(InterfazVistaPrincipal.PROMOCION_DORADA);;
        this.panelPromociones.add(this.botonPromocionDorada);
        
        this.botonPromocionPlatino = new JButton("Promocion Platino");
        this.botonPromocionPlatino.setActionCommand(InterfazVistaPrincipal.PROMOCION_PLATINO);
        this.panelPromociones.add(this.botonPromocionPlatino);
        
        this.panelTecnicos = new JPanel();
        this.panelTecnicos.setBorder(new TitledBorder(null, "Tecnicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.panelAccionesSistema.add(this.panelTecnicos);
        
        this.botonGestionarTecnicos = new JButton("Gestionar Tecnicos");
        this.botonGestionarTecnicos.setActionCommand(InterfazVistaPrincipal.MOSTRAR_TECNICOS);
        this.panelTecnicos.add(this.botonGestionarTecnicos);
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
    
    private ModeloTablaFacturas getModeloTablaFacturas() {
        return (ModeloTablaFacturas) this.tablaFacturas.getModel();
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
            this.panelAbonado.setVisible(false);
            return;
        }
        
        this.panelAbonado.setVisible(true);
        this.actualizarTablaContratos(abonado.getContratos());
        this.actualizarTablaFacturas(abonado.getFacturasEmitidas());
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
    
    /**
     * Actualiza la tabla de facturas con datos nuevos
     * 
     * @param facturas Los facturas a utilizar
     */
    @Override
    public void actualizarTablaFacturas(List<IFactura> facturas) {
        this.getModeloTablaFacturas().actualizar(facturas);
    }
    
    @Override
    public void mostrarAlertaPagarSinContratos() {
        JOptionPane.showMessageDialog(this.frame, "El abonado necesita al menos 1 contrato para poder abonar");
    }
    
    @Override
    public void mostrarDialogoFactura(IFactura factura) {
        JTextArea textarea = new JTextArea();
        textarea.setText(factura.getConcepto());
        JOptionPane.showMessageDialog(this.frame, textarea);
    }
    
    @Override
    public void actualizarBotonesPromocion(IPromocion promo) {
        //TODO refactorizar, se pueden hacer subclases de jbutton que implementen
        //su propia logica de enable
        if (promo instanceof SinPromocion) {
            this.botonSinPromocion.setEnabled(false);
            this.botonPromocionDorada.setEnabled(true);
            this.botonPromocionPlatino.setEnabled(true);
        }
        if (promo instanceof PromocionDorada) {
            this.botonSinPromocion.setEnabled(true);
            this.botonPromocionDorada.setEnabled(false);
            this.botonPromocionPlatino.setEnabled(true);
        }
        if (promo instanceof PromocionPlatino) {
            this.botonSinPromocion.setEnabled(true);
            this.botonPromocionDorada.setEnabled(true);
            this.botonPromocionPlatino.setEnabled(false);
        }
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }
}