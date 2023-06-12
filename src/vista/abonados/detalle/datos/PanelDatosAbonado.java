package vista.abonados.detalle.datos;

import modelo.AbonadoFisico;
import modelo.interfaces.IAbonado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelDatosAbonado extends JPanel {
    private final JLabel labelNombreAbonado;
    private final JLabel labelDniAbonado;
    private final JLabel labelTipoAbonado;
    private final JLabel labelEstadoAbonado;

    public void actualizar(IAbonado abonado) {
        this.labelDniAbonado.setText("Dni: " + abonado.getDni());
        this.labelNombreAbonado.setText("Nombre: " + abonado.getNombre());

        String tipoAbonado = abonado.isFisico() ? "Fisico" : "Juridico";
        this.labelTipoAbonado.setText("Tipo: " + tipoAbonado);

        String estadoAbonado = abonado.isFisico() ? ((AbonadoFisico) abonado).getEstado().toString() : "-";

        StringBuilder textoLabel = new StringBuilder();
        String color;

        textoLabel.append("<html>Estado: ");
        textoLabel.append(" <font color='");

        switch (estadoAbonado.toLowerCase()) {
            case "moroso" -> color = "red";
            case "con contrataciones" -> color = "green";
            default -> color = "black";
        }

        textoLabel.append(color);
        textoLabel.append("'>");
        textoLabel.append(estadoAbonado);
        textoLabel.append("</font></html>");

        this.labelEstadoAbonado.setText(textoLabel.toString());
    }

    public PanelDatosAbonado() {
        super();
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setBorder(new EmptyBorder(8, 8, 8, 8));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.labelNombreAbonado = new JLabel();
        this.add(this.labelNombreAbonado);

        this.labelDniAbonado = new JLabel();
        this.add(this.labelDniAbonado);

        this.labelTipoAbonado = new JLabel();
        this.add(this.labelTipoAbonado);

        this.labelEstadoAbonado = new JLabel();

        this.add(this.labelEstadoAbonado);
    }
}
