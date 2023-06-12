package vista;

import modelo.AbonadoFisico;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import modelo.tecnicos.ServicioTecnico;
import modelo.tecnicos.Tecnico;
import vista.abonados.*;
import vista.abonados.detalle.contratos.PanelContratos;
import vista.abonados.detalle.servicio.PanelServicioTecnico;
import vista.abonados.tabla.NuevoAbonadoDTO;
import vista.abonados.tabla.PanelTablaAbonados;
import vista.abonados.detalle.contratos.DialogoNuevoContrato;
import vista.abonados.detalle.contratos.ModeloTablaContratos;
import vista.abonados.detalle.contratos.NuevoContratoDTO;
import vista.abonados.detalle.facturas.DialogoFacturaPagada;
import vista.abonados.detalle.facturas.ModeloTablaFacturas;
import vista.sistema.PanelAccionesSistema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;

public class VentanaPrincipal implements InterfazVistaPrincipal, ChangeListener {

    private JFrame frame;
    private ActionListener actionListener;

    private DialogoNuevoContrato dialogoNuevoContrato;
    private DialogoFacturaPagada dialogoFacturaPagada;
    private EstadoDialogoAlerta estadoDialogoAlerta;

    private PanelServicioTecnico panelServicioTecnico;
    private PanelTablaAbonados panelTablaAbonados;
    private PanelAccionesSistema panelAccionesSistema;
    private PanelContratos panelContratos;

    private JPanel panelPrincipalAbonado;
    private JPanel panelAbonado;
    private JPanel panelAccionesAbonado;
    private JButton botonBorrarAbonado;
    private JPanel panelDatosAbonado;
    private JLabel labelNombreAbonado;
    private JLabel labelDniAbonado;
    private JLabel labelTipoAbonado;
    private JLabel labelEstadoAbonado;
    private JButton botonPagarEfectivo;
    private JTabbedPane tabsAbonado;
    private JPanel panelFacturas;
    private JScrollPane panelTablaFacturas;
    private JTable tablaFacturas;
    private JButton botonPagarTarjeta;
    private JButton botonPagarCheque;

    public void setActionListener(ActionListener listener) {
        panelServicioTecnico.setActionListener(listener);
        panelAccionesSistema.setActionListener(listener);
        panelTablaAbonados.setActionListener(listener);
        panelContratos.setActionListener(listener);
        this.botonPagarTarjeta.addActionListener(listener);
        this.botonPagarCheque.addActionListener(listener);
        this.botonPagarEfectivo.addActionListener(listener);
        this.actionListener = listener;
    }

    /**
     * Create the application.
     */
    public VentanaPrincipal() {
        initialize();
        dialogoNuevoContrato = new DialogoNuevoContrato(this.frame);
        dialogoFacturaPagada = new DialogoFacturaPagada(this.frame);
        estadoDialogoAlerta = new EstadoDialogoAlerta(this.frame);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.frame = new JFrame("Sistema de Contratación de Servicios de Seguridad - Grupo 10");
        this.frame.setBounds(100, 100, 1366, 768);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(new BorderLayout(0, 0));

        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionListener.actionPerformed(new ActionEvent(frame, 0, InterfazVistaPrincipal.CIERRE_VENTANA_PRINCIPAL));
                frame.dispose();
            }
        });

        this.panelTablaAbonados = new PanelTablaAbonados(this.frame);
        this.frame.getContentPane().add(this.panelTablaAbonados, BorderLayout.WEST);

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

        this.panelServicioTecnico = new PanelServicioTecnico();
        this.panelAccionesAbonado.add(this.panelServicioTecnico);

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

        this.panelContratos = new PanelContratos();

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
        this.tablaFacturas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actionListener.actionPerformed(new ActionEvent(tablaFacturas, 0, "SELECCION_FACTURA"));
            }
        });

        this.panelTablaFacturas.setViewportView(this.tablaFacturas);
        this.tabsAbonado.addTab("Facturas", this.panelFacturas);
        this.panelAbonado.add(this.panelPrincipalAbonado);

        this.panelAccionesSistema = new PanelAccionesSistema();
        this.frame.getContentPane().add(this.panelAccionesSistema, BorderLayout.NORTH);

        this.frame.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Muestra un alert indicando que ya existe un contrato con el mismo domicilio
     */
    @Override
    public void mostrarAlertaDomicilioDuplicado() {
        this.dialogoNuevoContrato.mostrarAlertaDomicilioDuplicado();
    }

    @Override
    public void mostrarAlertaFacturaPagada() {
        this.dialogoFacturaPagada.mostrarDialogoFacturaPagada();
    }

    @Override
    public void mostrarDialogoAlertaEstado(String msg) {
        this.estadoDialogoAlerta.mostrarDialogoAlertaEstado(msg);
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

    @Override
    public void mostrarAlertaAbonadoYaExiste() {
        this.panelTablaAbonados.mostrarAlertaAbonadoYaExiste();
    }

    private ModeloTablaFacturas getModeloTablaFacturas() {
        return (ModeloTablaFacturas) this.tablaFacturas.getModel();
    }

    /**
     * @return El Dni del abonado seleccionado, o null si no hay selección.
     */
    @Override
    public String obtenerAbonadoSeleccionado() {
        return this.panelTablaAbonados.obtenerAbonadoSeleccionado();
    }

    @Override
    public NuevoAbonadoDTO pedirNuevoAbonado() {
        return this.panelTablaAbonados.pedirNuevoAbonado();
    }

    @Override
    public Integer obtenerFacturaSeleccionado() {
        int fila = this.tablaFacturas.getSelectedRow();
        if (fila == -1) return -1;

        return (Integer) this.tablaFacturas.getValueAt(fila, 0);
    }

    /**
     * @return El domicilio del contrato seleccionado, o null si no hay selección.
     */
    @Override
    public String obtenerContratoSeleccionado() {
        return this.panelContratos.obtenerContratoSeleccionado();
    }

    /**
     * Actualiza la tabla de abonados con datos nuevos
     *
     * @param abonados Los abonados a utilizar
     */
    @Override
    public void actualizarTablaAbonados(List<IAbonado> abonados) {
        this.panelTablaAbonados.actualizar(abonados);
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

        String tipoAbonado = abonado.isFisico() ? "Fisico" : "Juridico";
        this.labelTipoAbonado.setText("Tipo: " + tipoAbonado);

        String estadoAbonado = abonado.isFisico() ? ((AbonadoFisico) abonado).getEstado().toString() : "-";
        this.labelEstadoAbonado.setText("Estado: " + estadoAbonado);


        ServicioTecnico servicio = abonado.getServicioTecnico();
        if (servicio != null) {
            servicio.addObserver(this);
        }

        this.actualizarProgresoServicio(servicio);
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
        this.panelContratos.actualizar(contratos);
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
    public void mostrarAlertaPagarSinFacturas() {
        JOptionPane.showMessageDialog(this.frame, "El abonado necesita al menos 1 factura para poder abonar");
    }

    @Override
    public void mostrarDialogoFactura(IFactura factura) {
        JTextArea textarea = new JTextArea();
        textarea.setText(factura.getConcepto());
        JOptionPane.showMessageDialog(this.frame, textarea);
    }

    @Override
    public void actualizarBotonesPromocion(IPromocion promo) {
        this.panelAccionesSistema.actualizarBotonesPromocion(promo);
    }

    @Override
    public void actualizarFecha(LocalDate fecha) {
        this.panelAccionesSistema.actualizarFecha(fecha);
    }

    @Override
    public boolean confirmarBorrarContrato() {
        String domicilio = this.obtenerContratoSeleccionado();
        int result = JOptionPane.showConfirmDialog(
                this.frame,
                "Está seguro que desea eliminar el contrato con domicilio " + domicilio + "?"
        );

        return result == JOptionPane.OK_OPTION;
    }

    @Override
    public boolean confirmarPagarFactura() {
        Integer id = this.obtenerFacturaSeleccionado();
        int result = JOptionPane.showConfirmDialog(
                this.frame,
                "Está seguro que desea pagar el contrato con id " + id + "?"
        );

        return result == JOptionPane.OK_OPTION;
    }

    @Override
    public boolean confirmarAvanzarMes() {
        return this.panelAccionesSistema.confirmarAvanzarMes();
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    public void actualizarComboboxTecnicos(List<Tecnico> tecnicos) {
        this.panelServicioTecnico.actualizarComboboxTecnicos(tecnicos);
    }

    public void actualizarProgresoServicio(ServicioTecnico service) {
        this.panelServicioTecnico.actualizarServicio(service);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ServicioTecnico) {
            ServicioTecnico service = (ServicioTecnico) o;
            if (service.getAbonado().getDni().equals(this.obtenerAbonadoSeleccionado())) {
                this.actualizarProgresoServicio(service);
            }
        }
    }

    public String obtenerTecnicoSeleccionado() {
        return this.panelServicioTecnico.obtenerTecnicoSeleccionado();
    }
}
