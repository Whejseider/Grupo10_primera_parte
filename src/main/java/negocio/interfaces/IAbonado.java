package negocio.interfaces;

public interface IAbonado extends IFactura, Cloneable {

    void agregaContrato(IContrato contrato);

    int cantidadDeContratos();

    Object clone() throws CloneNotSupportedException;

}
