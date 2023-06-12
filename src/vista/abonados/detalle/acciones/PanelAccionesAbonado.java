package vista.abonados.detalle.acciones;

import vista.InterfazVistaPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class PanelAccionesAbonado extends JPanel {
    private final JButton botonBorrarAbonado;
    private final JButton botonPagarEfectivo;
    private final JButton botonPagarTarjeta;
    private final JButton botonPagarCheque;

    public void setActionListener(ActionListener listener) {
        this.botonPagarTarjeta.addActionListener(listener);
        this.botonPagarCheque.addActionListener(listener);
        this.botonPagarEfectivo.addActionListener(listener);
        this.botonBorrarAbonado.addActionListener(listener);
    }

    private void inicializarComandos() {
        this.botonPagarCheque.setActionCommand(InterfazVistaPrincipal.PAGAR_FACTURA_CHEQUE);
        this.botonBorrarAbonado.setActionCommand(InterfazVistaPrincipal.BORRAR_ABONADO);
        this.botonPagarEfectivo.setActionCommand(InterfazVistaPrincipal.PAGAR_FACTURA_EFECTIVO);
        this.botonPagarTarjeta.setActionCommand(InterfazVistaPrincipal.PAGAR_FACTURA_TARJETA);
    }

    public PanelAccionesAbonado() {
        super();
        this.setBorder(new EmptyBorder(0, 8, 4, 0));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.botonBorrarAbonado = new JButton("Borrar abonado");
        this.add(this.botonBorrarAbonado);

        this.botonPagarEfectivo = new JButton("Pagar con efectivo");
        this.add(this.botonPagarEfectivo);

        this.botonPagarTarjeta = new JButton("Pagar con tarjeta");
        this.add(this.botonPagarTarjeta);

        this.botonPagarCheque = new JButton("Pagar con cheque");
        this.add(this.botonPagarCheque);

        inicializarComandos();
    }
}
