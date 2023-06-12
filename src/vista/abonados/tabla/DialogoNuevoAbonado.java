package vista.abonados.tabla;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DialogoNuevoAbonado {
    private JRadioButton radioFisico, radioJuridico;
    private JTextField inputNombre, inputDni;
    private JFrame frame;

    public DialogoNuevoAbonado(JFrame frame) {
        this.frame = frame;
        
        this.radioFisico = new JRadioButton("Físico");
        this.radioJuridico = new JRadioButton("Jurídico");
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioFisico);
        buttonGroup.add(radioJuridico);
        
        this.inputNombre = new JTextField();
        this.inputDni = new JTextField();
    }
    
    public void mostrarAlertaAbonadoYaExiste() {
        JOptionPane.showMessageDialog(frame, "Ya existe un abonado con el mismo DNI", "Abonado ya existe", JOptionPane.ERROR_MESSAGE);
    }

    public NuevoAbonadoDTO pedirNuevoAbonado() {
        int resultado = JOptionPane.showOptionDialog(
            frame, 
            new Object[] {radioFisico, radioJuridico, "Nombre", inputNombre, "DNI", inputDni},
            "Nuevo abonado", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null
        );
        
        if (resultado == JOptionPane.OK_OPTION) {
            String nombre = inputNombre.getText();
            String dni = inputDni.getText();
            String tipo = null;
            
            if (radioFisico.isSelected()) {
                tipo = "FISICO";
            }
            if (radioJuridico.isSelected()) {
                tipo = "JURIDICO";
            }
            
            boolean condicion = tipo != null && !nombre.isEmpty() && !dni.isEmpty();
            
            if (!condicion) {
                JOptionPane.showMessageDialog(frame, "Parametros invalidos!");
                return this.pedirNuevoAbonado();
            }
            return new NuevoAbonadoDTO(tipo, nombre, dni);
        }
        
        return null;
    }
}
