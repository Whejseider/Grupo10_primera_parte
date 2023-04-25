package modelo.domicilio;

import java.util.Objects;

public class Domicilio implements Cloneable {
    private String calle;
    private String numero;

    /**
     * Constructor de domicilio que genera un domicilio con una calle y el número
     *
     * @param calle  calle del domicilio
     * @param numero numero del comicilio
     */
    public Domicilio(String calle, String numero) {
        assert !Objects.equals(calle, "") : "Debe ingresar una calle!";
        assert !Objects.equals(numero, "") : "Debe ingresar el numero del domicilio!";

        this.calle = calle;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        assert !Objects.equals(calle, "") : "Debe ingresar una calle!";
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        assert !Objects.equals(numero, "") : "Debe ingresar el numero del domicilio!";

        this.numero = numero;
    }

    @Override
    public Object clone() {
        Object domicilioClonado = null;
        try {
            domicilioClonado = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return domicilioClonado;
    }
}
