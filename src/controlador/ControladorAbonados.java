package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import modelo.excepciones.AbonadoDuplicadoException;
import vista.InterfazVista;
import vista.NuevoAbonadoDTO;

public class ControladorAbonados implements ActionListener {
    private Sistema modelo;
    private InterfazVista vista;

    public ControladorAbonados(InterfazVista vista, Sistema modelo) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.vista.actualizarTablaAbonados(modelo.getAbonados());
        this.modelo = modelo;
    }
    
    private void manejarNuevoAbonado() {
        NuevoAbonadoDTO dto = this.vista.pedirNuevoAbonado();
        if (dto != null) {
            try {
                this.modelo.agregarAbonado(dto.getTipo(), dto.getNombre(), dto.getDni());
                this.vista.actualizarTablaAbonados(this.modelo.getAbonados());
            } catch (AbonadoDuplicadoException e) {
                this.vista.mostrarAlertaAbonadoYaExiste();
            }
        }
    }
    
    private void manejarBorrarAbonado() {
        String dni = this.vista.obtenerAbonadoSeleccionado();
        //Si no hay un abonado seleccionado no se hace nada
        if (dni == null) {
            return;
        }
        
        if (this.vista.confirmarBorrarAbonado()) {
            this.modelo.eliminarAbonado(dni);
            this.vista.actualizarTablaAbonados(this.modelo.getAbonados());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento){
        String comando = evento.getActionCommand();

        switch(comando) {
            case InterfazVista.NUEVO_ABONADO:
                manejarNuevoAbonado();
                break;
            case InterfazVista.BORRAR_ABONADO:
                manejarBorrarAbonado();
                break;
        }
    }
}
