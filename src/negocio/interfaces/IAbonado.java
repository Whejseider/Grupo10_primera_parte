package negocio.interfaces;

public interface IAbonado extends IFactura {
    public double getPago(double costoContrato);

    public void agregaContrato(IContrato contrato);

}
