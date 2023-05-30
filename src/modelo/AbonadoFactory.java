package modelo;

import modelo.interfaces.IAbonado;

/**
 * Factory para abonados.
 */
public class AbonadoFactory {

    /**
     * Genera un abonado de cierto tipo<br>
     * Ya sea de tipo <b>Fisico</b> o <b>Juridico</b>
     * 
     * @param tipo   Tipo de abonado Fisico o Juridico
     * @param nombre Nombre del abonado
     * @param dni    DNI del abonado
     * @return abonado de tipo Fisico o Juridico
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
