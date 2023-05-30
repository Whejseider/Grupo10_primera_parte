package modelo.excepciones;

public class AbonadoNoExisteException extends Exception {
    public AbonadoNoExisteException(String dni) {
        super("el Abonado con dni " + dni + " no existe");
    }
}
