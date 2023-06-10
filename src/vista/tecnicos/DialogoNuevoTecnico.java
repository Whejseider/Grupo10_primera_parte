package vista.tecnicos;

import javax.swing.*;

public class DialogoNuevoTecnico {
    private final JDialog frame;
    private final JTextField inputNombre;

    public DialogoNuevoTecnico(JDialog frame) {
        this.frame = frame;

        this.inputNombre = new JTextField();
    }

    public NuevoTecnicoDTO pedirNuevoTecnico() {
        int resultado = JOptionPane.showOptionDialog(
                frame,
                new Object[] {"Nombre", inputNombre},
                "Nuevo técnico",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null
        );

        if (resultado == JOptionPane.OK_OPTION) {
            String nombreTecnico = this.inputNombre.getText();

            boolean condicion = nombreTecnico != null && !nombreTecnico.isEmpty() ;

            if (!condicion) {
                JOptionPane.showMessageDialog(frame, "Formato incorrecto");
                return this.pedirNuevoTecnico();
            }

            return new NuevoTecnicoDTO(nombreTecnico);
        }

        return null;
    }

    public void mostrarAlertaTecnicoYaExiste() {
        JOptionPane.showMessageDialog(frame, "Parametros invalidos!");
    }
}
