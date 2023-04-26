package negocio.abonado;

import negocio.interfaces.IAbonado;

public class AbonadoFactory {

    /**
     * Genera un abonado de tipo Abonado Fisico o Juridico
     * @param tipo El tipo de abonado Fisico o Juridico
     * @param nombre nombre del abonado
     * @param dni dni del abonado
     * @return El abonado que se generara dependiendo del tipo
     */
    public static IAbonado getAbonado(String tipo, String nombre, String dni) {
        IAbonado respuesta = null;

        if (tipo.equalsIgnoreCase("Fisico"))
            respuesta = new AbonadoFisico(nombre, dni);
        else if (tipo.equalsIgnoreCase("Juridico"))
            respuesta = new AbonadoJuridico(nombre, dni);

        return respuesta;
    }
}
