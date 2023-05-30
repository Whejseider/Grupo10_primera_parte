package modelo.excepciones;

import modelo.interfaces.IAbonado;

public class AbonadoDuplicadoException extends Exception {
    public AbonadoDuplicadoException(IAbonado abonado) {
        super("El abonado " + abonado.toString() + " esta duplicado");
    }
}
