package modelo.excepciones;

public class ServicioEnCursoException extends Exception{
    public ServicioEnCursoException(String dni) {
        super("Ya hay un servicio en curso para el abonado con dni " + dni + ". Espere a que finalice para inciar otro.");
    }
}
