package negocio.excepciones;

import negocio.interfaces.IAbonado;

public class AbonadoNoExisteException extends Exception {
    public AbonadoNoExisteException(IAbonado abonado) {
        super("el Abonado " + abonado.toString() + " no existe");
    }
}
