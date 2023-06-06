package modelo.prueba;

/**
 * Corre todas las pruebas especificas de clases.
 */
public class Prueba {
  public static void main(String args[]) {
    PruebaContrato pruebasContrato = new PruebaContrato();
    pruebasContrato.allTestsContrato();
    PruebaAbonado pruebasAbonado = new PruebaAbonado();
    try {
//      pruebasAbonado.testClonacionDecorator();
    } catch (Exception e) {
      System.out.println("excepcion");
    }
    pruebasAbonado.allTestAbonado();
    System.out.println("todas las pruebas pasadas");

  }
}
