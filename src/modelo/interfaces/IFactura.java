package modelo.interfaces;

import java.io.Serializable;
import java.time.LocalDate;

public interface IFactura extends Cloneable, Serializable {


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
