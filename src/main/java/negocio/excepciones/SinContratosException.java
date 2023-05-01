package negocio.excepciones;

public class SinContratosException extends Exception {
	public SinContratosException() {
		super("No se puede facturar porque el abonado no tiene contratos. Debe tener al menos 1 contrato para poder generar una factura.");
	}
}
