package modelo.interfaces;

public interface IFactura extends Cloneable {
    boolean isPagada();

    void setPagada(boolean pagada);

    String getDetalle();

    String getConcepto();

    double getSubtotal();

    double getValorNeto();

    void setValorNeto(double valorNeto);

    IFactura clone() throws CloneNotSupportedException;

}
