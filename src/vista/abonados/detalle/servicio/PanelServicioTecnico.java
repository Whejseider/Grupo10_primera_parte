package vista.abonados.detalle.servicio;

import modelo.tecnicos.ITecnico;
import modelo.tecnicos.ServicioTecnico;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelServicioTecnico extends JPanel {
    private final JLabel labelServicioTecnico;
    private final JComboBox<String> comboBoxTecnicos;
    private final JButton botonEnviarTecnico;
    private final JPanel panelEnviarTecnico;
    private final JProgressBar progresoTecnico;

    public void setActionListener(ActionListener listener) {
        this.botonEnviarTecnico.addActionListener(listener);
    }

    public PanelServicioTecnico() {
        this.setBorder(new TitledBorder(null, "Servicio tecnico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.labelServicioTecnico = new JLabel("No hay ningun servicio en curso");
        this.add(this.labelServicioTecnico);

        this.progresoTecnico = new JProgressBar();
        this.add(this.progresoTecnico);

        this.panelEnviarTecnico = new JPanel();
        this.add(this.panelEnviarTecnico);

        this.botonEnviarTecnico = new JButton("Enviar Tecnico");
        this.botonEnviarTecnico.setActionCommand("ENVIAR_TECNICO");
        this.panelEnviarTecnico.add(this.botonEnviarTecnico);

        this.comboBoxTecnicos = new JComboBox<>();
        this.comboBoxTecnicos.setPreferredSize(new Dimension(150, 30));
        this.comboBoxTecnicos.setMaximumRowCount(5);
        this.panelEnviarTecnico.add(this.comboBoxTecnicos);
    }

    public void actualizarComboboxTecnicos(List<ITecnico> tecnicos) {
        this.comboBoxTecnicos.removeAllItems();
        if (tecnicos.size() == 0) {
            this.comboBoxTecnicos.setEnabled(false);
            this.botonEnviarTecnico.setEnabled(false);
        } else {
            this.comboBoxTecnicos.setEnabled(true);
            this.botonEnviarTecnico.setEnabled(true);
        }
        for (ITecnico tecnico : tecnicos) {
            this.comboBoxTecnicos.addItem(tecnico.getNombre());
        }
    }

    private void actualizarServicioEnCurso(int progreso) {
        this.botonEnviarTecnico.setEnabled(false);
        this.comboBoxTecnicos.setEnabled(false);
        this.progresoTecnico.setVisible(true);
        this.progresoTecnico.setValue(progreso);
    }

    private void actualizarServicioEsperandoTecnico() {
        this.progresoTecnico.setVisible(false);
        this.botonEnviarTecnico.setEnabled(false);
        this.comboBoxTecnicos.setEnabled(false);
    }

    private void actualizarServicioFinalizado() {
        this.progresoTecnico.setVisible(false);
        this.botonEnviarTecnico.setEnabled(true);
        this.comboBoxTecnicos.setEnabled(true);
    }

    private void actualizarServicioSinIniciar() {
        this.progresoTecnico.setVisible(false);
        this.botonEnviarTecnico.setEnabled(true);
        this.comboBoxTecnicos.setEnabled(true);
        this.labelServicioTecnico.setText("Sin servicio técnico en curso");
    }

    public void actualizarServicio(ServicioTecnico service) {
        if (service == null) {
            actualizarServicioSinIniciar();
            return;
        }

        if (service.getEstado().isEnCurso()) {
            actualizarServicioEnCurso(service.getProgreso());
        } else if (service.getEstado().isEsperandoTecnico()) {
            actualizarServicioEsperandoTecnico();
        } else if (service.getEstado().isFinalizado()) {
            actualizarServicioFinalizado();
        }

        this.labelServicioTecnico.setText(service.getEstado().getTextoEstado());
    }

    public String obtenerTecnicoSeleccionado() {
        return (String) this.comboBoxTecnicos.getSelectedItem();
    }
}
