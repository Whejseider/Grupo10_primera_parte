package vista.abonados.detalle.contratos;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class DialogoNuevoContrato {
    private JRadioButton radioVivienda, radioComercio;
    private JTextField inputDomicilio;
    private JSpinner inputCamaras, inputBotones;
    private JCheckBox inputTieneMovil;
    private JFrame frame;
    

    public DialogoNuevoContrato(JFrame frame) {
        this.frame = frame;
        
        this.radioVivienda = new JRadioButton("Vivienda");
        this.radioComercio = new JRadioButton("Comercio");
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioVivienda);
        buttonGroup.add(radioComercio);
        
        this.inputDomicilio = new JTextField();
        
        SpinnerNumberModel modeloBotones = new SpinnerNumberModel(0, 0, 99, 1);
        SpinnerNumberModel modeloCamaras = new SpinnerNumberModel(0, 0, 99, 1);
        this.inputBotones = new JSpinner(modeloBotones);
        this.inputCamaras = new JSpinner(modeloCamaras);
        
        this.inputTieneMovil = new JCheckBox();
    }
    
    public void mostrarAlertaDomicilioDuplicado() {
        JOptionPane.showMessageDialog(frame, "Ya existe un contrato en el sistema con el mismo domicilio. Por favor elija otro.", "Domicilio existente", JOptionPane.ERROR_MESSAGE);
    }
    
    private String obtenerTipoContrato() {
        String tipo = null;
        
        if (radioVivienda.isSelected()) {
            tipo = "VIVIENDA";
        }
        if (radioComercio.isSelected()) {
            tipo = "COMERCIO";
        }
        
        return tipo;
    }

    public NuevoContratoDTO pedirNuevoContrato() {
        int resultado = JOptionPane.showOptionDialog(
            frame, 
            new Object[] {radioVivienda, radioComercio, 
                    "Domicilio", 
                    inputDomicilio, 
                    "Cámaras de video", 
                    inputCamaras, 
                    "Botones antipánico", 
                    inputBotones, 
                    "Móvil de acompañamiento", 
                    inputTieneMovil
                    },
            "Nuevo contrato", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null
        );
        
        if (resultado == JOptionPane.OK_OPTION) {
            String domicilio = inputDomicilio.getText();
            int camaras = (int) inputCamaras.getModel().getValue();
            int botones = (int) inputBotones.getModel().getValue();
            boolean tieneMovil = inputTieneMovil.isSelected();
            String tipo = obtenerTipoContrato();
            
            
            boolean condicion = tipo != null && !domicilio.isEmpty() && !tipo.isEmpty();
            
            if (!condicion) {
                JOptionPane.showMessageDialog(frame, "Parametros invalidos!");
                return this.pedirNuevoContrato();
            }
            
            return new NuevoContratoDTO(tipo, domicilio, tieneMovil, camaras, botones );
        }
        
        return null;
    }
}
