package modelo.decorators;

import modelo.interfaces.IFactura;

import java.text.DecimalFormat;

public class FacturaMorosoRecargoDecorator extends PagoDecorator {
    private final double modificador = 1.3;

    public FacturaMorosoRecargoDecorator(IFactura factura) {
        super(factura);
    }

    /**
     * Obtiene los detalles de la factura y el valor neto con el recargo aplicado.
     * Se usa un formato para los numeros flotantes
     * @return los detalles de la factura y el valor neto con el recargo aplicado
     */
    @Override
    public String getDetalle() {
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        return this.getFactura().getDetalle() + "\n TOTAL CON RECARGO: $" + numberFormat.format(this.getValorNeto()) + "\n";
    }

    /**
     * Obtiene el valor neto aplicado con el recargo
     * @return el valor neto con recargo
     */
    @Override
    public double getValorNeto() {
        return this.getFactura().getValorNeto() * modificador;
    }

    @Override
    public IFactura clone() throws CloneNotSupportedException {

        return (IFactura) super.clone();
    }
}
