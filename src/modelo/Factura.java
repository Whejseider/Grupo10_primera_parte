package modelo;

import modelo.interfaces.IFactura;

import java.time.LocalDate;

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
    private LocalDate fecha;

    public Factura(String concepto, double subtotal, double valorNeto, LocalDate fecha) {
        assert concepto != null && !concepto.isEmpty();
        assert subtotal >= 0;
        assert valorNeto >= 0;
        assert fecha != null;

        this.valorNeto = valorNeto;
        this.concepto = concepto;
        this.subtotal = subtotal;
        this.id = numero++;
        this.fecha = fecha;
    }

    public static void actualizarID(int maxId){
        numero = maxId + 1;
    }

    public LocalDate getFecha() {
        return fecha;
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
    
    public int getId() {
        return id;
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
        detalle.append("FECHA (YYYY/MM/DD): " + fecha.toString() + "\n");
        detalle.append("PAGADA: " + (isPagada()?"Si":"No") + "\n");
        detalle.append(concepto + "\n");
        detalle.append("SUBTOTAL: $" + subtotal + "\n");

        return detalle.toString();
    }

    public static int getNumero() {
        return numero;
    }

    public static void setNumero(int numero) {
        Factura.numero = numero;
    }
}
