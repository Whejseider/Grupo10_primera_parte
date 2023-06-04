package vista;

import java.awt.event.ActionListener;
import java.util.List;

import modelo.interfaces.IAbonado;
import modelo.interfaces.IContrato;
import vista.abonados.NuevoAbonadoDTO;
import vista.contratos.NuevoContratoDTO;

public interface InterfazVista {
    static final String NUEVO_ABONADO = "NUEVO_ABONADO";
    static final String BORRAR_ABONADO = "BORRAR_ABONADO";
    static final String SELECCION_ABONADO = "SELECCION_ABONADO"; 
    static final String NUEVO_CONTRATO = "NUEVO_CONTRATO";

    public void setActionListener(ActionListener actionListener);
    
    public void mostrarAlertaAbonadoYaExiste();
    public boolean confirmarBorrarAbonado();
    public String obtenerAbonadoSeleccionado();
    public NuevoAbonadoDTO pedirNuevoAbonado();
    public void actualizarTablaAbonados(List<IAbonado> abonados);
    public void actualizarDetallesAbonado(IAbonado abonado);
    
    public void mostrarAlertaDomicilioDuplicado();
    public NuevoContratoDTO pedirNuevoContrato();
    public void actualizarTablaContratos(List<IContrato> contratos);
}
