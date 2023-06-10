package modelo.excepciones;

public class TecnicoTrabajandoException extends Exception{
    public TecnicoTrabajandoException() {
        super("El tecnico está trabajando, no puede ser eliminado");
    }
}
