package prueba;

import negocio.servicioAlarma.ServicioAlarma;
import negocio.promocion.SinPromocion;
import negocio.contrato.ContratoComercio;
import negocio.contrato.ContratoVivienda;
import negocio.promocion.PromocionDorada;
import negocio.promocion.PromocionPlatino;

public class PruebaContrato {
  public static void main(String args[]) {
    PromocionDorada promoDorada = new PromocionDorada();
    PromocionPlatino promoPlatino = new PromocionPlatino();
    SinPromocion sinPromo = new SinPromocion();
    
    // --- TEST PRECIOS ---
    ContratoComercio contrato1 = new ContratoComercio("Alvarez 1234", new ServicioAlarma(false,3,2));
    assert contrato1.getPrecio(promoDorada) == 20500 : "Precio de promo dorada mal calculado";
    assert contrato1.getPrecio(promoPlatino) == 14950 : "Precio de promo platino mal calculado";  
    assert contrato1.getPrecio(sinPromo) == 23000 : "Precio sin promo mal calculado";
    
    ContratoVivienda contratoVivienda = new ContratoVivienda("Independencia 1234", new ServicioAlarma());
    assert contratoVivienda.getPrecio(promoDorada) == 7000 : "Precio de promo dorada mal calculado";
    assert contratoVivienda.getPrecio(promoPlatino) == 5950 : "Precio de promo platino mal calculado";  
    assert contratoVivienda.getPrecio(sinPromo) == 8500 : "Precio sin promo mal calculado";
    
    // --- TEST IGUALDAD ---
    ContratoComercio contrato2 = new ContratoComercio("Alvarez 1234", new ServicioAlarma());
    ContratoVivienda contrato3 = new ContratoVivienda("Alsina 43", new ServicioAlarma());
    
    assert contrato1.equals(contrato2);
    assert contrato1.equals(contrato1);
    assert !contrato3.equals(contrato1);
    
    System.out.println("Pruebas de contrato bien");
  }
}
