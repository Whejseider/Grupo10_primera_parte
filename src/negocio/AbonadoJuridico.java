package negocio;

public class AbonadoJuridico extends Abonado {

    public AbonadoJuridico(String nombre, String dni) {
        super(nombre, dni);
    }

    @Override
    public double getPagoMedioDePago() {
        if (this.cantidadDeContratos() >= 3)
            return getPagoNeto() * 0.5;
        return getPagoNeto();
    }

}
