package prueba;

import negocio.Abonado;
import negocio.AbonadoFactory;
import negocio.AbonadoFisico;
import negocio.AbonadoJuridico;
import negocio.ContratoFactory;
import negocio.ContratoVivienda;
import negocio.PromocionDorada;
import negocio.PromocionPlatino;
import negocio.ServicioAlarma;
import negocio.SinPromocion;
import negocio.decorators.PagoEfectivoDecorator;
import negocio.decorators.PagoTarjetaCreditoDecorator;
import negocio.excepciones.SinContratosException;
import negocio.interfaces.IAbonado;
import negocio.interfaces.IContrato;
import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

/**
 * Clase con pruebas para la clase Abonado.
 * 
 * Se encarga de correr test para facturación, clonación y cálculo de pagos.
 */
public class PruebaAbonado {
	private IPromocion sinPromo = new SinPromocion();

	public void allTestAbonado() {
		testPago();
		testDecorator();
		testClonacionAbonadoJuridico();
		try {
			testClonacionAbonadoFisico();
			testClonacionServicioAlarma();
			testClonacionArrayList();
			testClonacionDecorator();
			testFacturacion();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Fallaron test de abonado. Terminando");
		} catch (SinContratosException e) {
			throw new RuntimeException("Fallaron test de abonado. Terminando");
		}
	}

	/**
	 * Prueba que el pago del abonado calcule correctamente el precio de sus
	 * contratos.
	 */
	public void testPago() {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");

		abonado.agregaContrato(contratoVivienda);

		assert Math.round(abonado.getPagoNeto(new SinPromocion())) == 21000;
		assert Math.round(abonado.getPagoNeto(new PromocionDorada())) == 19500;
		assert Math.round(abonado.getPagoNeto(new PromocionPlatino())) == 14700;

		System.out.println("Pruebas de pago de abonado bien");
	}

	/**
	 * Prueba que los decorators de pago descuenten de manera correcta.
	 */
	public void testDecorator() {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");

		abonado.agregaContrato(contratoVivienda);

		IAbonado abonado1 = new PagoEfectivoDecorator(abonado);

		assert Math.round(abonado1.getPagoMedioDePago(new SinPromocion())) == 16800;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionDorada())) == 15600;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionPlatino())) == 11760;

		System.out.println("Pruebas de pago para decorator de abonado bien");
	}

	/**
	 * Prueba que no se puedan clonar abonados de tipo jurídico
	 */
	public void testClonacionAbonadoJuridico() {
		Abonado abonadoJuridico = new AbonadoJuridico("235245234", "Pablo");

		try {
			abonadoJuridico.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Prueba clonar abonado juridico bien");
		}
	}

	/**
	 * Prueba que se puedan clonar abonados de tipo físico
	 */
	public void testClonacionAbonadoFisico() throws CloneNotSupportedException {
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		IAbonado clonAbonado = abonado.clone();

		assert (clonAbonado != null);
		System.out.println("Prueba clonar abonado fisico bien");
	}

	/**
	 * Prueba que modificando un contrato cuya referencia esta en el abonado
	 * original, no se modifica el abonado clonado
	 */
	public void testClonacionServicioAlarma() throws CloneNotSupportedException {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		abonado.agregaContrato(contratoVivienda);
		IAbonado clonAbonado = abonado.clone();

		contratoVivienda.agregarBtnesAntipanico(1);
		assert (clonAbonado.getPagoNeto(sinPromo) == 21000);
		assert (abonado.getPagoNeto(sinPromo) == 23000);
	}

	/**
	 * Prueba que la lista de contratos se crea correctamente al clonar un abonado
	 */
	public void testClonacionArrayList() throws CloneNotSupportedException {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		abonado.agregaContrato(contratoVivienda);
		IAbonado clonAbonado;

		clonAbonado = abonado.clone();

		ContratoVivienda contratoVivienda2 = new ContratoVivienda("contrato2", new ServicioAlarma(true, 1, 1));
		clonAbonado.agregaContrato(contratoVivienda2);

		// Prueba que agregar contratos al abonado clonado no agrega al original

		assert clonAbonado.cantidadDeContratos() == 2;
		assert abonado.cantidadDeContratos() == 1;

	}

	/**
	 * Pruebas para la clonacion de un abonado si está decorado con un medio de
	 * pago.
	 */
	public void testClonacionDecorator() throws CloneNotSupportedException {
		// Prueba que se pueda clonar abonados con decorator de pago

		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		IAbonado abonadoCheque = new PagoEfectivoDecorator(abonado);
		IAbonado clonAbonadoCheque = null;

		clonAbonadoCheque = abonadoCheque.clone();

		assert (clonAbonadoCheque.getDni() == abonado.getDni());
		assert (clonAbonadoCheque.getNombre() == abonado.getNombre());
	}

	/**
	 * Pruebas sobre la facturación de un abonado. Prueba:
	 * 
	 * <ul>
	 * 
	 * <li>Que no se pueda facturar si el abonado no tiene contratos.</li>
	 * 
	 * <li>Que el detalle de las facturas muestre el nombre y dni del abonado con el
	 * total con y sin descuento de sus contratos.</li>
	 * 
	 * <li>Que se puedan clonar las facturas.</li>
	 * 
	 * </ul>
	 */
	public void testFacturacion() throws SinContratosException, CloneNotSupportedException {
		IContrato contratoVivienda = ContratoFactory.getContrato("Vivienda", "Alsina 1234", true, 1, 1);
		IContrato contratoComercio = ContratoFactory.getContrato("Comercio", "Alsina 12345", false, 2, 3);
		IAbonado abonado = new PagoTarjetaCreditoDecorator(AbonadoFactory.getAbonado("Fisico", "Pepe", "41352345"));

		// ---- TEST FACTURACION SIN CONTRATOS

		try {
			abonado.facturar(sinPromo);
		} catch (SinContratosException e) {
			assert abonado.cantidadDeFacturas() == 0;
		}

		// ---- TEST GENERACION DE FACTURAS

		abonado.agregaContrato(contratoVivienda);
		abonado.facturar(sinPromo);
		abonado.agregaContrato(contratoComercio);
		abonado.facturar(sinPromo);

		assert abonado.cantidadDeFacturas() == 2;

		// ---- TEST DETALLE DE FACTURAS

		String detalleFacturas = abonado.getDetalleFacturas();

		String[] stringsEsperadasEnFactura = { abonado.getDni(), abonado.getNombre(),
				Double.toString(abonado.getPagoMedioDePago(sinPromo)), Double.toString(abonado.getPagoNeto(sinPromo)),
				Double.toString(contratoVivienda.getPrecio(sinPromo)),
				Double.toString(contratoComercio.getPrecio(sinPromo)) };

		assert detalleFacturas != null && !detalleFacturas.isEmpty();

		for (String str : stringsEsperadasEnFactura) {
			assert detalleFacturas.contains(str) : "La factura no muestra " + str;
		}

		// ---- TEST CLONACION DE FACTURAS

		IFactura factura = abonado.generarFactura(sinPromo);
		factura.clone();

		System.out.println("Pruebas de facturacion de abonado bien");
	}
}