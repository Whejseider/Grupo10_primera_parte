package vista.tecnicos;

import modelo.tecnicos.Tecnico;

import java.awt.event.ActionListener;
import java.util.List;

public interface InterfazVentanaTecnicos {
    String NUEVO_TECNICO = "NUEVO_TECNICO";
    String BORRAR_TECNICO = "BORRAR_TECNICO";
    void setVisible(boolean visible);

    void actualizar(List<Tecnico> tecnicos);
    void setActionListener(ActionListener listener);

    NuevoTecnicoDTO pedirNuevoTecnico();
    public String obtenerTecnicoSeleccionado();
    void mostrarAlertaTecnicoYaExiste();
    void mostrarAlertaTecnicoNoSePuedeBorrar();
}
