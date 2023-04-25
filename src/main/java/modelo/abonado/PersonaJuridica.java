package modelo.abonado;

import modelo.contrato.IContrato;

public class PersonaJuridica extends Abonado {
    public PersonaJuridica(String nombre, String dni) {
        super(nombre, dni);
    }


    /**
     * Pago del servicio de persona Juridica<br>
     * En el tercer pago se aplica el descuento del 50%
     * @param contrato El contrato que se desea saber el precio a pagar
     * @param i Numero de iteracion usado para persona juridica
     * @return precio del pago del servicio de una persona Juridica
     */
    @Override
    public double getPagoDeServicio(IContrato contrato, int i) {
        double pago;

        pago = contrato.getPrecio();
        if (i > 1)
            pago *= porcBonificacionJuridica;

        return pago;
    }

    @Override
    public String getTipo() {
        return "Juridica";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
