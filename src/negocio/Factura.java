package negocio;

import negocio.interfaces.IFactura;

public class Factura implements IFactura {
	private final int cantCamaras;
	private final int cantBotones;
	private final int cantMoviles;
	private final double precioTotal;
	private final double precioSinDescuento;
	private final int cantServiciosVivienda;
	private final int cantServiciosComercio;
	private final String dniAbonado;
	private final String nombreAbonado;
	private final int id;
	
	static int numero = 1;
	
	/**
	 * Constructor de la clase factura. Espera que se pasen como parametro los datos relevantes del abonado y sus contratos
	 * para generar un detalle.
	 */
	public Factura(String dniAbonado, String nombreAbonado, int cantCamaras, int cantBotones, int cantMoviles, 
			int cantServiciosVivienda, int cantServiciosComercio, double precioTotal, double precioSinDescuento) {
		assert cantCamaras >= 0;
		assert cantBotones >= 0;
		assert cantMoviles >= 0;
		assert precioTotal >= 0;
		assert precioSinDescuento >= 0;
		assert dniAbonado != null && !dniAbonado.isEmpty();
		assert nombreAbonado != null && !nombreAbonado.isEmpty();
		
		this.cantCamaras = cantCamaras;
		this.cantBotones = cantBotones;
		this.cantMoviles = cantMoviles;
		this.precioTotal = precioTotal;
		this.precioSinDescuento = precioSinDescuento;
		this.cantServiciosVivienda = cantServiciosVivienda;
		this.cantServiciosComercio = cantServiciosComercio;
		this.dniAbonado = dniAbonado;
		this.nombreAbonado = nombreAbonado;
		this.id = numero++;
	}
	
	private String getDetalleServicio(String nombre, int cantidad) {
		if (cantidad > 0) {
			return nombre + " x" + cantidad + "\n";
		}
		
		return "";
	}

	/**
	 * Genera una string con el detalle de la factura.
	 * 
	 * El detalle incluye los datos del abonado, la cantidad de servicios contratados, y el precio total y subtotal.
	 */
	public String getDetalle() {
		StringBuilder detalle = new StringBuilder();
		String separador = "----------------\n";
		
		detalle.append("Factura N°" + this.id + "\n");
		detalle.append("DNI: " + this.dniAbonado + "\n");
		detalle.append("Nombre: " + this.nombreAbonado + "\n");
		detalle.append(separador);
		detalle.append(getDetalleServicio("Servicio Comercio", this.cantServiciosComercio));
		detalle.append(getDetalleServicio("Servicio Vivienda", this.cantServiciosVivienda));
		detalle.append(getDetalleServicio("Camaras", this.cantCamaras));
		detalle.append(getDetalleServicio("Botones antipanico", this.cantBotones));
		detalle.append(getDetalleServicio("Moviles", this.cantMoviles));
		detalle.append(separador);
		detalle.append("Total sin descuento: $" + this.precioSinDescuento + "\n");
		detalle.append("TOTAL: $" + this.precioTotal);
		
		return detalle.toString();
	}
	
	/**
	 * Clona la factura
	 */
	@Override
	public Factura clone() throws CloneNotSupportedException
	{
		return (Factura) super.clone();
	}
}
