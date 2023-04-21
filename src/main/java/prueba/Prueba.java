package prueba;

import modelo.alarma.ISistemaAlarma;
import modelo.alarma.SistemaAlarmaFactory;

public class Prueba {

    public static void main(String[] args) {

        ISistemaAlarma alarma1 = SistemaAlarmaFactory.getAlarma("Vivienda");
        ISistemaAlarma alarma2 = SistemaAlarmaFactory.getAlarma("Comercio");

        System.out.println(alarma1.getDetalles());
        System.out.println();

        alarma1.agregarCamara(2);
        alarma1.agregarBtnAntiPanico(3);
        alarma1.agregarMovilSeguimiento();
        System.out.println(alarma1.getDetalles());


    }
}
