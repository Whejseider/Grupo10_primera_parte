package vista.sistema;

import modelo.PromocionDorada;
import modelo.PromocionPlatino;
import modelo.SinPromocion;
import modelo.interfaces.IPromocion;
import vista.InterfazVistaPrincipal;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PanelAccionesSistema extends JPanel {
    private final JPanel panelPromociones;
    private final JButton botonSinPromocion;
    private final JButton botonPromocionDorada;
    private final JButton botonPromocionPlatino;
    private final JPanel panelTecnicos;
    private final JButton botonGestionarTecnicos;
    private final JPanel panelFecha;
    private final JLabel labelFecha;
    private final JButton botonAvanzarMes;

    public void setActionListener(ActionListener listener) {
        botonAvanzarMes.addActionListener(listener);
        botonGestionarTecnicos.addActionListener(listener);
        botonPromocionPlatino.addActionListener(listener);
        botonPromocionDorada.addActionListener(listener);
        botonSinPromocion.addActionListener(listener);
    }

    private void inicializarComandos() {
        this.botonSinPromocion.setActionCommand(InterfazVistaPrincipal.PROMOCION_NINGUNA);
        this.botonPromocionDorada.setActionCommand(InterfazVistaPrincipal.PROMOCION_DORADA);
        this.botonPromocionPlatino.setActionCommand(InterfazVistaPrincipal.PROMOCION_PLATINO);
        this.botonGestionarTecnicos.setActionCommand(InterfazVistaPrincipal.MOSTRAR_TECNICOS);
        this.botonAvanzarMes.setActionCommand(InterfazVistaPrincipal.AVANZAR_MES);
    }

    public PanelAccionesSistema() {
        super();
        this.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));

        this.panelPromociones = new JPanel();
        FlowLayout flowLayout = (FlowLayout) this.panelPromociones.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        this.panelPromociones.setBorder(new TitledBorder(null, "Promociones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(this.panelPromociones);

        this.botonSinPromocion = new JButton("Sin Promocion");
        this.panelPromociones.add(this.botonSinPromocion);

        this.botonPromocionDorada = new JButton("Promocion Dorada");

        this.panelPromociones.add(this.botonPromocionDorada);

        this.botonPromocionPlatino = new JButton("Promocion Platino");
        this.panelPromociones.add(this.botonPromocionPlatino);

        this.panelTecnicos = new JPanel();
        this.panelTecnicos.setBorder(new TitledBorder(null, "Tecnicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(this.panelTecnicos);

        this.botonGestionarTecnicos = new JButton("Gestionar Tecnicos");

        this.panelTecnicos.add(this.botonGestionarTecnicos);

        this.panelFecha = new JPanel();
        this.panelFecha.setBorder(new TitledBorder(null, "Sistema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(this.panelFecha);

        this.labelFecha = new JLabel(LocalDate.now().toString());
        this.panelFecha.add(this.labelFecha);

        this.botonAvanzarMes = new JButton("Avanzar mes");

        this.inicializarComandos();

        this.panelFecha.add(this.botonAvanzarMes);
    }

    public void actualizarBotonesPromocion(IPromocion promo) {
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

    public void actualizarFecha(LocalDate fecha) {
        this.labelFecha.setText(fecha.toString());
    }

    public boolean confirmarAvanzarMes() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Está seguro que desea avanzar de mes? Se generarán facturas para todos los abonados con los contratos actuales."
        );

        return result == JOptionPane.OK_OPTION;
    }
}
