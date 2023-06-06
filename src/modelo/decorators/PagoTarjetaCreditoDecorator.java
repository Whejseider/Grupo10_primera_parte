package modelo.decorators;

import modelo.interfaces.IFactura;

import java.text.DecimalFormat;

/**
 * Decorador para pagos con tarjeta de crédito.
 */
public class PagoTarjetaCreditoDecorator extends PagoDecorator {
    private final double modificador = 1.05;

    public PagoTarjetaCreditoDecorator(IFactura factura) {
        super(factura);
    }


    /**
     * Obtiene los detalles de la factura y el valor neto con el aumento aplicado.
     * Se usa un formato para los numeros flotantes
     * @return los detalles de la factura y el valor neto con el aumento aplicado
     */
    @Override
    public String getDetalle() {
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        return this.getFactura().getDetalle() + "\n TOTAL: $" + numberFormat.format(this.getValorNeto()) + "\n";
    }

    /**
     * Obtiene el valor neto aplicado con el aumento por tarjeta de credito
     * @return el valor neto con aumento por tarjeta de credito
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
