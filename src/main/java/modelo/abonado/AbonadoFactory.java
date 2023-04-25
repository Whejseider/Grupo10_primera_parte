package modelo.abonado;

public class AbonadoFactory {

    public static IAbonado getAbonado(String tipo, String nombre, String dni) {
        IAbonado respuesta = null;

        if (tipo.equalsIgnoreCase("Fisica"))
            respuesta = new PersonaFisica(nombre, dni);
        else if (tipo.equalsIgnoreCase("Juridica"))
            respuesta = new PersonaJuridica(nombre, dni);

        return respuesta;
    }
}
