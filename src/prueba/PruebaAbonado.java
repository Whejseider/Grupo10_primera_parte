package prueba;

import negocio.Abonado;
import negocio.AbonadoFisico;
import negocio.AbonadoJuridico;
import negocio.ContratoVivienda;
import negocio.PagoEfectivoDecorator;
import negocio.PromocionDorada;
import negocio.PromocionPlatino;
import negocio.ServicioAlarma;
import negocio.SinPromocion;
import negocio.interfaces.IAbonado;

public class PruebaAbonado {

	public void allTestAbonado() {
		testPago();
		testDecorator();
		testClonacionAbonadoJuridico();
		testClonacionAbonadoFisico();
		try {
			testClonacionServicioAlarma();
			testClonacionArrayList();
			testClonacionDecorator();
		} catch (Exception e) {

		}
	}

	public void testPago() {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");

		abonado.agregaContrato(contratoVivienda);

		assert Math.round(abonado.getPagoNeto(new SinPromocion())) == 21000;
		assert Math.round(abonado.getPagoNeto(new PromocionDorada())) == 19500;
		assert Math.round(abonado.getPagoNeto(new PromocionPlatino())) == 14700;
	}

	public void testDecorator() {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");

		abonado.agregaContrato(contratoVivienda);

		IAbonado abonado1 = new PagoEfectivoDecorator(abonado);

		assert Math.round(abonado1.getPagoMedioDePago(new SinPromocion())) == 16800;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionDorada())) == 15600;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionPlatino())) == 11760;

		System.out.println("Pruebas decorator de abonado bien");
	}

	public void testClonacionAbonadoJuridico() {
		Abonado abonadoJuridico = new AbonadoJuridico("235245234", "Pablo");

		try {
			abonadoJuridico.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Prueba clonar abonado juridico bien");
		}
	}

	public void testClonacionAbonadoFisico() {
		// Prueba que se puedan clonar abonados de tipo físico

		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		try {
			IAbonado clonAbonado = null;
			clonAbonado = abonado.clone();
			assert (clonAbonado != null);
			System.out.println("Prueba clonar abonado fisico bien");
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}

	public void testClonacionServicioAlarma() throws CloneNotSupportedException {
		// Prueba que modificando un contrato cuya referencia esta en el abonado
		// original,
		// no se modifica el abonado clonado
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		IAbonado clonAbonado = abonado.clone();

		contratoVivienda.agregarBtnesAntipanico(1);
		assert clonAbonado.getPagoNeto(new SinPromocion()) == 21000;
		assert abonado.getPagoNeto(new SinPromocion()) == 23000;
	}

	public void testClonacionArrayList() throws CloneNotSupportedException {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		abonado.agregaContrato(contratoVivienda);
		IAbonado clonAbonado = abonado.clone();

		// Prueba que agregar contratos al abonado clonado no agrega al original

		assert clonAbonado.cantidadDeContratos() == 2;
		assert abonado.cantidadDeContratos() == 1;

	}

	public void testClonacionDecorator() throws CloneNotSupportedException {
		// Prueba que se pueda clonar abonados con decorator de pago

		Abonado abonado = new AbonadoFisico("41352345", "Pepe");
		IAbonado clonAbonadoCheque = new PagoEfectivoDecorator(abonado);
		IAbonado clonAbonadoChequeClon = null;

		clonAbonadoChequeClon = clonAbonadoCheque.clone();
		assert (clonAbonadoChequeClon.getDni() == abonado.getDni());
		assert (clonAbonadoChequeClon.getNombre() == abonado.getNombre());

	}

	/*
	 * public void pruebasFacturacion() {
	 * abonado.facturar(new SinPromocion());
	 * abonado.agregaContrato(contratoComercio);
	 * IAbonado abonadoCheque = new PagoChequeDecorator(abonado);
	 * abonadoCheque.facturar(new SinPromocion());
	 * 
	 * System.out.println(abonadoCheque.getDetalleFacturas());
	 * 
	 * System.out.println("Pruebas abonado bien");
	 * 
	 * }
	 */

}