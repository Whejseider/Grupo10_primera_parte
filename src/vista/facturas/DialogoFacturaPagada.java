package vista.facturas;

import javax.swing.*;

public class DialogoFacturaPagada {

    private JFrame frame;

    public DialogoFacturaPagada(JFrame frame) {
        this.frame = frame;
    }

    public void mostrarDialogoFacturaPagada() {
        JOptionPane.showMessageDialog(frame, "La factura ya está paga!", "Factura pagada", JOptionPane.ERROR_MESSAGE);
    }
}
