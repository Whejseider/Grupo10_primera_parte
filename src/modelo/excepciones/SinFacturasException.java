package modelo.excepciones;

public class SinFacturasException extends Exception {

    public SinFacturasException() {
        super("No se puede facturar porque el abonado no tiene contratos. Debe tener al menos 1 contrato para poder generar una factura.");

    }
}
