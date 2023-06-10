package vista.tecnicos;

import java.awt.event.ActionListener;

public interface InterfazVentanaTecnicos {
    String NUEVO_TECNICO = "NUEVO_TECNICO";
    String BORRAR_TECNICO = "BORRAR_TECNICO";
    void setVisible(boolean visible);

    //TODO: agregar tecnicos
    void actualizar();
    void setActionListener(ActionListener listener);

    NuevoTecnicoDTO pedirNuevoTecnico();
    void mostrarAlertaTecnicoYaExiste();
}
