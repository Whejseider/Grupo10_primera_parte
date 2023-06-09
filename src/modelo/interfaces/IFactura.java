package modelo.interfaces;

public interface IFactura extends Cloneable {
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
