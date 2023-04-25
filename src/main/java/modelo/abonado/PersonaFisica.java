package modelo.abonado;

import modelo.contrato.IContrato;

public class PersonaFisica extends Abonado {
    public PersonaFisica(String nombre, String dni) {
        super(nombre, dni);
    }


    @Override
    public String getTipo() {
        return "Fisica";
    }

    @Override
    public Object clone() {
        Object clon = null;

        try {
            clon = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clon;
    }
}
