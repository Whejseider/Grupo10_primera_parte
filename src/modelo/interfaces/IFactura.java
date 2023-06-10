package modelo.interfaces;

import java.io.Serializable;

public interface IFactura extends Cloneable, Serializable {
    boolean isPagada();

    void setPagada(boolean pagada);

    String getDetalle();

    public String getConcepto();

    public int getId();

    double getSubtotal();

    double getValorNeto();

    void setValorNeto(double valorNeto);

    IFactura clone() throws CloneNotSupportedException;

}
