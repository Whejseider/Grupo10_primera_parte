package modelo;

import modelo.interfaces.IFactura;

/**
 * Representa una factura con un concepto, subtotal y valor neto.
 */
public class Factura implements IFactura {
    private final String concepto;
    private final double subtotal;
    private double valorNeto;
    private final int id;
    private static int numero = 1;
    private boolean pagada = false;

    public Factura(String concepto, double subtotal, double valorNeto) {
        assert concepto != null && !concepto.isEmpty();
        assert subtotal >= 0;
        assert valorNeto >= 0;
        
        this.valorNeto = valorNeto;
        this.concepto = concepto;
        this.subtotal = subtotal;
        this.id = numero++;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(double valorNeto) {
        assert valorNeto >= 0;
        this.valorNeto = valorNeto;
    }

    public IFactura clone() throws CloneNotSupportedException {
        return (IFactura) super.clone();
    }

    public String getDetalle() {
        StringBuilder detalle = new StringBuilder();

        detalle.append("FACTURA N°" + id + "\n\n");
        detalle.append("PAGADA: " + (isPagada()?"Si":"No") + "\n");
        detalle.append(concepto + "\n");
        detalle.append("SUBTOTAL: $" + subtotal + "\n");
//        detalle.append("TOTAL: $" + valorNeto + "\n");

        return detalle.toString();
    }
}
