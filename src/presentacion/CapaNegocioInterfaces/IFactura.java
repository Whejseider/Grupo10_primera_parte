package presentacion.CapaNegocioInterfaces;

public interface IFactura extends Cloneable {
    public String generaFactura(IAbonado abonado);

    public IFactura getInstance();
}
