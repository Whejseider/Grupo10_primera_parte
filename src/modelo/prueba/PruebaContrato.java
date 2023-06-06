package modelo.prueba;

import modelo.*;

/**
 * Clase con pruebas para la clase Contrato.
 * 
 * Se encarga de correr test para cálculo de precios e igualdad.
 */
public class PruebaContrato {
  private PromocionDorada promoDorada = new PromocionDorada();
  private PromocionPlatino promoPlatino = new PromocionPlatino();
  private SinPromocion sinPromo = new SinPromocion();

  public void allTestsContrato() {
    testIgualdad();
    testPrecios();
  }

  public void testIgualdad() {
    ContratoComercio contrato1 = new ContratoComercio("Alvarez 1234", new ServicioAlarma(false, 3, 2));
    ContratoComercio contrato2 = new ContratoComercio("Alvarez 1234", new ServicioAlarma());
    ContratoVivienda contrato3 = new ContratoVivienda("Alsina 43", new ServicioAlarma());
    assert contrato1.equals(contrato2);
    assert contrato1.equals(contrato1);
    assert !contrato3.equals(contrato1);
  }

  public void testPrecios() {
    ContratoVivienda contratoVivienda = new ContratoVivienda("Independencia 1234", new ServicioAlarma());
    assert contratoVivienda.getPrecio(promoDorada) == 7000 : "Precio de promo dorada mal calculado";
    assert contratoVivienda.getPrecio(promoPlatino) == 5950 : "Precio de promo platino mal calculado";
    assert contratoVivienda.getPrecio(sinPromo) == 8500 : "Precio sin promo mal calculado";

    ContratoComercio contratoComercio = new ContratoComercio("Alvarez 1234", new ServicioAlarma(false, 3, 2));
    assert contratoComercio.getPrecio(promoDorada) == 20500 : "Precio de promo dorada mal calculado";
    assert contratoComercio.getPrecio(promoPlatino) == 14950 : "Precio de promo platino mal calculado";
    assert contratoComercio.getPrecio(sinPromo) == 23000 : "Precio sin promo mal calculado";
  }

}
