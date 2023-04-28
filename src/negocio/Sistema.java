package negocio;

import java.util.HashMap;
import java.util.Iterator;

import negocio.interfaces.IAbonado;
import negocio.interfaces.IContrato;
import negocio.interfaces.IPromocion;

public class Sistema {
	private HashMap<String, IAbonado> abonados;
	private IPromocion promo;
	
	public Sistema() {
		this.abonados = new HashMap<String, IAbonado>();
		this.promo = new SinPromocion();
	}
	
	public void setPromo(IPromocion promo) {
		assert promo != null;
		this.promo = promo;
	}
		
	public void agregarAbonado(String tipo, String dni, String nombre) throws Exception {
		IAbonado abonado = this.abonados.putIfAbsent(dni, AbonadoFactory.getAbonado(tipo, dni, nombre));
		
		if (abonado != null) {
			throw new Exception("Ya existe un abonado con el mismo DNI");
		}
	}
	
	public void quitarAbonado(String dni) {
		assert dni != null && !dni.isEmpty();
		this.abonados.remove(dni);
	}
	
	private Iterator<IAbonado> getIteratorAbonados() {
		return this.abonados.values().iterator();
	}
	
	private IAbonado buscarAbonado(String dni) {
		return this.abonados.get(dni);
	}
	
	private boolean contratoExiste(IContrato contrato) {
		Iterator<IAbonado> iterator = this.getIteratorAbonados();

		while (iterator.hasNext()) {
			if (iterator.next().tieneContrato(contrato)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Agrega un nuevo contrato a un abonado
	 * @param dniAbonado El dni del abonado
	 * @param tipo El tipo de abonado a crear.
	 * @param domicilio El domicilio de contrato
	 * @param tieneMovil Si el contrato tiene movil
	 * @param cantCamaras Cantidad de camaras a asignarse al contrato
	 * @param cantBotones Cantidad de botones a asignarse al contrato
	 * @throws Exception
	 */
	public void agregarContrato(String dniAbonado, String tipo, String domicilio, boolean tieneMovil, int cantCamaras, int cantBotones) throws Exception {
		IAbonado abonado = this.buscarAbonado(dniAbonado);
		
		if (abonado == null)  {
			throw new Exception("Abonado no existe");
		}
		
		IContrato nuevoContrato = ContratoFactory.getContrato(tipo, domicilio, tieneMovil, cantBotones, cantCamaras);

		if (this.contratoExiste(nuevoContrato)) {
			throw new Exception("El contrato ya existe o tiene domicilio igual");
		}
		
		abonado.agregaContrato(nuevoContrato);
	}
	
	public double getPagoAbonado(String dniAbonado) {
		IAbonado abonado = this.buscarAbonado(dniAbonado);
		
		if (abonado != null) {
			return abonado.getPagoMedioDePago(this.promo);
		}
		
		return 0;
	}
}
