package modelo.interfaces;

import java.time.LocalDate;

public interface IFactura extends Cloneable {
    LocalDate getFecha();

    boolean isPagada();

    void setPagada(boolean pagada);

    String getDetalle();

    String getConcepto();

    int getId();

    double getSubtotal();

    double getValorNeto();

    void setValorNeto(double valorNeto);

    IFactura clone() throws CloneNotSupportedException;

}
