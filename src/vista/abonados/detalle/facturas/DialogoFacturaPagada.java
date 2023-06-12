package vista.abonados.detalle.facturas;

import javax.swing.*;

public class DialogoFacturaPagada {

    private JFrame frame;

    public DialogoFacturaPagada(JFrame frame) {
        this.frame = frame;
    }

    public void mostrarDialogoFacturaPagada() {
        JOptionPane.showMessageDialog(frame, "La factura ya esta paga o aún no puede pagar!", "Error al pagar", JOptionPane.ERROR_MESSAGE);
    }
}
