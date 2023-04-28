package negocio;

public class PruebaSistema {
	public static void main(String args[]) {
		Sistema sistema = new Sistema();
		
		try {
			sistema.agregarAbonado("juridico", "41715479", "Bautista");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			sistema.agregarContrato("41715479", "Vivienda", "Alsina 1234", true, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(sistema.getPagoAbonado("41715479"));
		sistema.setPromo(new PromocionPlatino());
		System.out.println(sistema.getPagoAbonado("41715479"));
	}
}
