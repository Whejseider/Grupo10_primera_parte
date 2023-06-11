package modelo;

import modelo.interfaces.IPromocion;

public class PromocionFactory {

    public static IPromocion getPromocion(String tipo){
        IPromocion respuesta = null;

        if(tipo.equalsIgnoreCase("Sin promocion"))
            respuesta = new SinPromocion();
        else if (tipo.equalsIgnoreCase("Promocion Dorada"))
            respuesta = new PromocionDorada();
        else if (tipo.equalsIgnoreCase("Promocion Platino"))
            respuesta = new PromocionPlatino();

        return respuesta;
    }
}
