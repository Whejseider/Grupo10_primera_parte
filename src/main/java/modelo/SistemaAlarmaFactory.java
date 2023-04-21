package modelo;

public class SistemaAlarmaFactory {

    public static ISistemaAlarma getAlarma(String tipo) {
        ISistemaAlarma respuesta = null;

        if (tipo.equalsIgnoreCase("Vivienda"))
            respuesta = new SistemaAlarmaVivienda();
//        else if (tipo.equalsIgnoreCase("Comercio"))
//            respuesta = new SistemaAlarmaComercio();


        return respuesta;
    }
}
