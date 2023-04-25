package modelo.abonado;

import modelo.contrato.IContrato;

public class PersonaJuridica extends Abonado {
    public PersonaJuridica(String nombre, String dni) {
        super(nombre, dni);
    }


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
