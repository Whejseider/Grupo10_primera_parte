package negocio.excepciones;

import negocio.Contrato;
import negocio.interfaces.IContrato;

public class ContratoDuplicadoException extends Exception {
    public ContratoDuplicadoException(IContrato contrato) {
        super("Ya existe un contrato con el mismo domicilio :" + contrato.toString());
    }
}
