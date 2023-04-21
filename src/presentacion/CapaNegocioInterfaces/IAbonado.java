package presentacion.CapaNegocioInterfaces;

public interface IAbonado {
    public double getPago(double costoContrato);

    public void agregaContrato(IContrato contrato);

    public String getDetalleContratos();
}
