package vista;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import modelo.interfaces.IAbonado;

public interface InterfazVista {
    static final String NUEVO_ABONADO = "NUEVO_ABONADO";

    public void setActionListener(ActionListener actionListener);
    
    public void mostrarAlertaAbonadoYaExiste();
    public NuevoAbonadoDTO pedirNuevoAbonado();
    public void actualizarTablaAbonados(List<IAbonado> abonados);
}
