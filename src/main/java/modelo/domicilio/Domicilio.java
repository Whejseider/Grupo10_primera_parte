package modelo.domicilio;

public class Domicilio implements Cloneable {
    private String calle;
    private String numero;

    public Domicilio(String calle, String numero) {
        this.calle = calle;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
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
