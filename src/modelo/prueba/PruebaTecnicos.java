package modelo.prueba;

import modelo.Sistema;
import modelo.excepciones.*;
import modelo.interfaces.IAbonado;
import modelo.tecnicos.ServicioTecnico;
import modelo.tecnicos.Tecnico;

import java.util.Observable;
import java.util.Observer;

public class PruebaTecnicos implements Observer {
    public void probarTecnicos() throws AbonadoDuplicadoException, ContratoDuplicadoException, AbonadoNoExisteException, ServicioEnCursoException {
        Sistema modelo = Sistema.getInstance();
        IAbonado abonado = modelo.agregarAbonado("fisico", "bautista", "234273");
        modelo.agregarContrato("234273", "vivienda", "alsina 1234", true, 3, 3);
        try {
            modelo.agregarTecnico("pepito");
        } catch (TecnicoYaExisteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        int progreso = (int) arg;
        System.out.println(progreso);
    }
}
