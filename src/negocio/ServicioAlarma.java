package negocio;

import negocio.interfaces.IServicioAlarma;

public class ServicioAlarma implements IServicioAlarma {
	private boolean tieneMovil;
	private int cantCamaras;
	private int cantBotonesAntipanico;
	
	static double precioCamara = 3000;
	static double precioMovil = 7500;
	static double precioBotonAntipanico = 2000;
	
	/**
     * Constructor para ServicioAlarma.
     *
     * @param tieneMovil Si debe contar con movil de acompañamiento
     * @param cantCamaras Cantidad de camaras de videovigilancia.
     * @param cantBotonesAntipanico Cantidad de botones antipánico.
     */
	public ServicioAlarma(boolean tieneMovil, int cantCamaras, int cantBotonesAntipanico) {
		assert cantCamaras >= 0 : "La cantidad de camaras no puede ser negativa";
		assert cantBotonesAntipanico >= 0 : "La cantidad de botones antipanico no puede ser negativa";

		this.tieneMovil = tieneMovil;
		this.cantCamaras = cantCamaras;
		this.cantBotonesAntipanico = cantBotonesAntipanico;
	}
	
	/**
	 * Constructor para ServicioAlarma donde no se incluye ningún servicio adicional.
	 */
	public ServicioAlarma() {
		this(false, 0, 0);
	};
	
	static void setPrecioCamara(double precio) {
		assert precio > 0 : "El precio debe ser mayor a cero";
		precioCamara = precio;
	}
	
	static void setPrecioBotonAntipanico(double precio) {
		assert precio > 0 : "El precio debe ser mayor a cero";
		precioBotonAntipanico = precio;
	}
	
	static void setPrecioMovil(double precio) {
		assert precio > 0 : "El precio debe ser mayor a cero";
		precioMovil = precio;
	}

	static double getPrecioCamara() {
		return precioCamara;
	}
	
	static double getPrecioBotonAntipanico() {
		return precioBotonAntipanico;
	}
	
	static double getPrecioMovil() {
		return precioMovil;
	}

	@Override
	public void agregarCamaras(int cantidad) {
		assert cantidad > 0 : "La cantidad debe ser mayor a cero";
		this.cantCamaras += cantidad;
	}

	@Override
	public void setCamaras(int cantidad) {
		assert cantidad >= 0 : "La cantidad no puede ser negativa";
		this.cantCamaras = cantidad;
	}

	@Override
	public void agregarBotonesAntipanico(int cantidad) {
		assert cantidad > 0 : "La cantidad debe ser mayor a cero";
		this.cantBotonesAntipanico += cantidad;
	}

	@Override
	public void setBotonesAntipanico(int cantidad) {
		assert cantidad >= 0 : "La cantidad no puede ser negativa";
		this.cantBotonesAntipanico = cantidad;
	}

	/**
	 * Agrega móvil de acompañamiento al servicio
	 */
	@Override
	public void contratarMovil() {
		this.tieneMovil = true;
	}

	/**
	 * Quita el movil de acompañamiento del servicio
	 */
	@Override
	public void quitarMovil() {
		this.tieneMovil = false;
	}

	@Override
	public int getCantCamaras() {
		return this.cantCamaras;
	}

	@Override
	public int getCantBotonesAntipanico() {
		return this.cantBotonesAntipanico;
	}

	@Override
	public boolean getTieneMovil() {
		return this.tieneMovil;
	}

}
