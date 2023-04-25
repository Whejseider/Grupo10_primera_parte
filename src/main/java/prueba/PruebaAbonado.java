package prueba;

import negocio.servicioAlarma.ServicioAlarma;
import negocio.promocion.SinPromocion;
import negocio.abonado.Abonado;
import negocio.abonado.AbonadoJuridico;
import negocio.contrato.ContratoVivienda;
import negocio.interfaces.IFactura;
import negocio.pago.PagoEfectivoDecorator;
import negocio.promocion.PromocionDorada;
import negocio.promocion.PromocionPlatino;

public class PruebaAbonado {
	public static void main(String args[]) {
		Abonado abonado = new AbonadoJuridico("41352345", "Pepe");

		abonado.agregaContrato(new ContratoVivienda("Alsina 1234", new ServicioAlarma(true, 1, 1)));

		// ---- TEST PAGO ----

		assert Math.round(abonado.getPagoNeto(new SinPromocion())) == 21000;
		assert Math.round(abonado.getPagoNeto(new PromocionDorada())) == 19500;
		assert Math.round(abonado.getPagoNeto(new PromocionPlatino())) == 14700;
		
		System.out.println("Pruebas abonado bien");
		
		// ---- TEST DECORATOR

		IFactura abonado1 = new PagoEfectivoDecorator(abonado);

		assert Math.round(abonado1.getPagoMedioDePago(new SinPromocion())) == 16800;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionDorada())) == 15600;
		assert Math.round(abonado1.getPagoMedioDePago(new PromocionPlatino())) == 11760;
		
		System.out.println("Pruebas decorator de abonado bien");
	}
}
