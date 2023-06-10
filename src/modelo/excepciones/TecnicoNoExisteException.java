package modelo.excepciones;

public class TecnicoNoExisteException extends Exception {
    public TecnicoNoExisteException() {
        super("el Tecnico con no existe");
    }
}
