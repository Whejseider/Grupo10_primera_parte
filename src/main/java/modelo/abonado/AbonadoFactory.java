package modelo.abonado;

public class AbonadoFactory {

    /**
     * Genera un abonado de tipo Persona Fisica o Juridica
     * @param tipo El tipo de abonado Fisica o Juridica
     * @param nombre nombre del abonado
     * @param dni dni del abonado
     * @return El abonado que se generara dependiendo del tipo
     */
    public static IAbonado getAbonado(String tipo, String nombre, String dni) {
        IAbonado respuesta = null;

        if (tipo.equalsIgnoreCase("Fisica"))
            respuesta = new PersonaFisica(nombre, dni);
        else if (tipo.equalsIgnoreCase("Juridica"))
            respuesta = new PersonaJuridica(nombre, dni);

        return respuesta;
    }
}
