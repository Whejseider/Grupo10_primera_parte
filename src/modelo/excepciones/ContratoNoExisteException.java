package modelo.excepciones;

public class ContratoNoExisteException extends Exception {
    public ContratoNoExisteException(String domicilio) {
        super("el contrato con domicilio " + domicilio + " no existe");
    }
}