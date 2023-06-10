package modelo.interfaces;

import java.io.Serializable;

public interface IEstadoFisico extends Serializable {

    void pagarFactura(IFactura factura);

    void contratarNuevoServicio(IContrato contrato);

    void bajaDeServicio(IContrato contrato);

}
