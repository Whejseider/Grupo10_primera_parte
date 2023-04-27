package prueba;

import negocio.Abonado;
import negocio.AbonadoFisico;
import negocio.AbonadoJuridico;
import negocio.Contrato;
import negocio.ContratoComercio;
import negocio.ContratoVivienda;
import negocio.PagoEfectivoDecorator;
import negocio.PromocionDorada;
import negocio.PromocionPlatino;
import negocio.ServicioAlarma;
import negocio.SinPromocion;
import negocio.interfaces.IAbonado;
import negocio.interfaces.IFactura;

public class PruebaAbonado {
	public static void main(String args[]) {
		ContratoVivienda contratoVivienda = new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1));
		Abonado abonado = new AbonadoFisico("41352345", "Pepe");

		abonado.agregaContrato(contratoVivienda);

		// ---- TEST PAGO ----

		assert Math.round(abonado.getPagoNeto(new SinPromocion())) == 21000;
		assert Math.round(abonado.getPagoNeto(new PromocionDorada())) == 19500;
		assert Math.round(abonado.getPagoNeto(new PromocionPlatino())) == 14700;

		// ---- TEST DECORATOR

		IFactura abonado1 = new PagoEfectivoDecorator(abonado);

		assert Math.round(abonado1.getPagoMedioDePago(new SinPromocion())) == 16800;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionDorada())) == 15600;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionPlatino())) == 11760;

		System.out.println("Pruebas decorator de abonado bien");

		// ---- TEST CLONACION ABONADO JURIDICO
		// Prueba que no se puedan clonar abonados de tipo jurídico

		Abonado abonadoJuridico = new AbonadoJuridico("235245234", "Pablo");

		try {
			abonadoJuridico.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Prueba clonar abonado juridico bien");
		}

		// ---- TEST CLONACION ABONADO
		// Prueba que se puedan clonar abonados de tipo físico

		IAbonado clonAbonado = null;
		try {
			clonAbonado = abonado.clone();
			System.out.println("Prueba clonar abonado fisico bien");
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		Contrato contratoComercio = new ContratoComercio("Falucho 1234", new ServicioAlarma(true, 1, 1));

		// --- TEST CLONACION SERVICIOALARMA
		// Prueba que modificando un contrato cuya referencia esta en el abonado
		// original,
		// no se modifica el abonado clonado

		contratoVivienda.agregarBtnesAntipanico(1);
		assert clonAbonado.getPagoNeto(new SinPromocion()) == 21000;
		assert abonado.getPagoNeto(new SinPromocion()) == 23000;

		// --- TEST CLONACION ARRAYLIST
		// Prueba que agregar contratos al abonado clonado no agrega al original
		clonAbonado.agregaContrato(contratoComercio);

		assert clonAbonado.cantidadDeContratos() == 2;
		assert abonado.cantidadDeContratos() == 1;

		// ---- TEST CLONACION DECORATOR
		// Prueba que se pueda clonar abonados con decorator de pago

		IFactura clonAbonadoCheque = new PagoEfectivoDecorator(abonado);
		IFactura clonAbonadoChequeClon = null;

		try {
			clonAbonadoChequeClon = clonAbonadoCheque.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		assert Math.round(clonAbonadoChequeClon.getPagoMedioDePago(new SinPromocion())) == 18400;

		System.out.println("Pruebas abonado bien");
	}
}