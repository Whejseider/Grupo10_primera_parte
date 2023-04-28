package negocio.interfaces;

public interface IAbonado extends IFacturable, IDetallable {

    public void agregaContrato(IContrato contrato);

    public int cantidadDeContratos();
    
    public boolean tieneContrato(IContrato contrato);
    
    public IAbonado clone() throws CloneNotSupportedException;
}
