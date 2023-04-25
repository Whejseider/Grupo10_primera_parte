package negocio.contrato;

import negocio.interfaces.IContrato;
import negocio.interfaces.IServicioAlarma;

public class ContratoFactory {

    /**
     * Genera un contrato de tipo Comercio o Vivienda
     * @param tipo El tipo de contrato Comercio o Vivienda
     * @param domicilio domicilio del abonado
     * @param servicioAlarma servicio de alarma de Vivienda o Comercio
     * @return El contrato que se generara dependiendo del tipo
     */
    public static IContrato getContrato(String tipo, String domicilio, IServicioAlarma servicioAlarma) {
        IContrato respuesta = null;

        if (tipo.equalsIgnoreCase("Vivienda"))
            respuesta = new ContratoVivienda(domicilio, servicioAlarma);
        else if (tipo.equalsIgnoreCase("Comercio"))
            respuesta = new ContratoComercio(domicilio, servicioAlarma);

        return respuesta;
    }
}
