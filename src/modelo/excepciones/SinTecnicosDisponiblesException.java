package modelo.excepciones;

public class SinTecnicosDisponiblesException extends Exception{
    public SinTecnicosDisponiblesException() {
        super("No hay tecnicos disponibles para hacer service");
    }
}
