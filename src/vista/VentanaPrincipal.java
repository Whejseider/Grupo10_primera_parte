package vista;

import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import modelo.interfaces.IFactura;
import modelo.interfaces.IPromocion;
import modelo.tecnicos.ITecnico;
import modelo.tecnicos.ServicioTecnico;

import vista.abonados.EstadoDialogoAlerta;
import vista.abonados.detalle.acciones.PanelAccionesAbonado;
import vista.abonados.detalle.contratos.NuevoContratoDTO;
import vista.abonados.detalle.contratos.PanelContratos;
import vista.abonados.detalle.datos.PanelDatosAbonado;
import vista.abonados.detalle.facturas.DialogoFacturaPagada;
import vista.abonados.detalle.facturas.PanelFacturas;
import vista.abonados.detalle.servicio.PanelServicioTecnico;
import vista.abonados.tabla.NuevoAbonadoDTO;
import vista.abonados.tabla.PanelTablaAbonados;
import vista.sistema.PanelAccionesSistema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;

public class VentanaPrincipal implements InterfazVistaPrincipal {
    private JFrame frame;
    private ActionListener actionListener;

    private DialogoFacturaPagada dialogoFacturaPagada;
    private EstadoDialogoAlerta estadoDialogoAlerta;

    private PanelServicioTecnico panelServicioTecnico;
    private PanelTablaAbonados panelTablaAbonados;
    private PanelAccionesSistema panelAccionesSistema;
    private PanelContratos panelContratos;
    private PanelFacturas panelFacturas;
    private PanelAccionesAbonado panelAccionesAbonado;
    private PanelDatosAbonado panelDatosAbonado;

    private JPanel panelPrincipalAbonado;
    private JPanel panelAbonado;
    private JTabbedPane tabsAbonado;

    public void setActionListener(ActionListener listener) {
        panelServicioTecnico.setActionListener(listener);
        panelAccionesSistema.setActionListener(listener);
        panelTablaAbonados.setActionListener(listener);
        panelContratos.setActionListener(listener);
        panelFacturas.setActionListener(listener);
        panelAccionesAbonado.setActionListener(listener);
        this.actionListener = listener;
    }

    /**
     * Create the application.
     */
    public VentanaPrincipal() {
        initialize();
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

        this.panelAccionesAbonado = new PanelAccionesAbonado();
        this.panelAbonado.add(this.panelAccionesAbonado, BorderLayout.EAST);

        this.panelServicioTecnico = new PanelServicioTecnico();
        this.panelAccionesAbonado.add(this.panelServicioTecnico);

        this.panelPrincipalAbonado = new JPanel();
        this.panelPrincipalAbonado.setLayout(new BorderLayout(0, 0));

        this.panelDatosAbonado = new PanelDatosAbonado();
        this.panelPrincipalAbonado.add(this.panelDatosAbonado, BorderLayout.NORTH);

        this.tabsAbonado = new JTabbedPane(JTabbedPane.TOP);

        this.panelContratos = new PanelContratos(this.frame);
        this.panelFacturas = new PanelFacturas(this.frame);

        this.tabsAbonado.addTab("Contratos", this.panelContratos);
        this.tabsAbonado.addTab("Facturas", this.panelFacturas);

        this.panelPrincipalAbonado.add(this.tabsAbonado);

        this.panelAbonado.add(this.panelPrincipalAbonado);

        this.panelAccionesSistema = new PanelAccionesSistema();
        this.frame.getContentPane().add(this.panelAccionesSistema, BorderLayout.NORTH);

        this.frame.setVisible(true);
    }

    /**
     * Muestra un alert indicando que ya existe un contrato con el mismo domicilio
     */
    @Override
    public void mostrarAlertaDomicilioDuplicado() {
        this.panelContratos.mostrarAlertaDomicilioDuplicado();
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
        return this.panelFacturas.obtenerFacturaSeleccionado();
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
        this.panelDatosAbonado.actualizar(abonado);


        ServicioTecnico servicio = abonado.getServicioTecnico();
        if (servicio != null) {
            servicio.addObserver(this);
        }

        this.actualizarProgresoServicio(servicio);
    }

    @Override
    public NuevoContratoDTO pedirNuevoContrato() {
        return this.panelContratos.pedirNuevoContrato();
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
        this.panelFacturas.actualizar(facturas);
    }

    @Override
    public void mostrarAlertaPagarSinFacturas() {
        JOptionPane.showMessageDialog(this.frame, "El abonado necesita al menos 1 factura para poder abonar");
    }

    @Override
    public void mostrarDialogoFactura(IFactura factura) {
        this.panelFacturas.mostrarDialogoFactura(factura);
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
        return this.panelContratos.confirmarBorrarContrato();
    }

    @Override
    public boolean confirmarPagarFactura() {
        return this.panelFacturas.confirmarPagarFactura();
    }

    @Override
    public boolean confirmarAvanzarMes() {
        return this.panelAccionesSistema.confirmarAvanzarMes();
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    public void actualizarComboboxTecnicos(List<ITecnico> tecnicos) {
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
