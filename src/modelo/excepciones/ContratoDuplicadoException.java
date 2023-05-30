package modelo.excepciones;

import modelo.interfaces.IContrato;

public class ContratoDuplicadoException extends Exception {
    public ContratoDuplicadoException(IContrato contrato) {
        super("Ya existe un contrato con el mismo domicilio :" + contrato.toString());
    }
}
