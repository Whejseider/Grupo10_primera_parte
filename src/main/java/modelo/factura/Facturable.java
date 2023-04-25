package modelo.factura;

import modelo.abonado.IAbonado;
import modelo.contrato.IContrato;

import java.util.List;

public interface Facturable extends Cloneable {

    double descEfectivo = 0.8;
    double incTarjeta = 1.05;
    double incCheque = 1.1;

//    double getPagoDeServicios();

    double getTotalAPagar();

    void setTotalAPagar(double totalAPagar);

    double getTotalAPagarMdP();

    void setTotalAPagarMdP(double totalAPagar);
    String getDetalles();

    IAbonado getAbonado();

    void setAbonado(IAbonado abonado);

    List<IContrato> getContrataciones();

    String getMedioDePago();

    void setMedioDePago(String medioDePago);

    public Object clone();

}
