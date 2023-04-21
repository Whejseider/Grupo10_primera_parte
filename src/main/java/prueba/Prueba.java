package prueba;

import modelo.*;

public class Prueba {

    public static void main(String[] args) {

        ISistemaAlarma alarma = SistemaAlarmaFactory.getAlarma("Vivienda");

        alarma.getDetalles();

    }
}
