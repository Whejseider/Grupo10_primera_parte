package modelo;

import modelo.interfaces.IContrato;
import modelo.interfaces.IServicioAlarma;

/**
 * Factory para contratos.
 */
public class ContratoFactory {

    /**
     * Genera un contrato de cierto tipo<br>
     * Ya sea de tipo <b>Vivienda</b> o <b>Comercio</b>
     *
     * @param tipo        Tipo de contrato. Vivienda o Comercio
     * @param domicilio   Domicilio del contrato
     * @param tieneMovil  Si tiene o no movil de seguimiento
     * @param cantBotones Cantidad de botones anti panico
     * @param cantCamaras Cantidad de camaras
     * @return Contrato de tipo Vivienda o Comercio
     */
    public static IContrato getContrato(String tipo, String domicilio, boolean tieneMovil,
                                        int cantBotones, int cantCamaras) {
        IContrato respuesta = null;
        IServicioAlarma servicioAlarma = new ServicioAlarma(tieneMovil, cantCamaras, cantBotones);

        if (tipo.equalsIgnoreCase("Vivienda"))
            respuesta = new ContratoVivienda(domicilio, servicioAlarma);
        else if (tipo.equalsIgnoreCase("Comercio"))
            respuesta = new ContratoComercio(domicilio, servicioAlarma);

        return respuesta;
    }
}
