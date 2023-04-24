package negocio.interfaces;

public interface IAbonado extends IFactura {

    public void agregaContrato(IContrato contrato);

    public int cantidadDeContratos();

}
