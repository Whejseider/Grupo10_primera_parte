package modelo.excepciones;

public class TecnicoYaExisteException extends Exception{
    public TecnicoYaExisteException() {
        super("Ya existe un tecnico con ese nombre");
    }
}
