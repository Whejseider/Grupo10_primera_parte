package vista.abonados;

import javax.swing.*;

public class EstadoDialogoAlerta {
    private JFrame frame;

    public EstadoDialogoAlerta(JFrame frame) {
        this.frame = frame;
    }

    public void mostrarDialogoAlertaEstado(String msg) {
        JOptionPane.showMessageDialog(this.frame, msg, "Operacion no permitida", JOptionPane.ERROR_MESSAGE);
    }
}
