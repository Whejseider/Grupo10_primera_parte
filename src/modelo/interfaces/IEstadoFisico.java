package modelo.interfaces;

public interface IEstadoFisico {

    void pagarFactura(IFactura factura);
    void contratarNuevoServicio(IContrato contrato);
    void bajaDeServicio(IContrato contrato);

}
