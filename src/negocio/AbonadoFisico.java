package negocio;

public class AbonadoFisico extends Abonado {

    public AbonadoFisico(String nombre, String dni) {
        super(nombre, dni);
    }

    @Override
    public double getPagoMedioDePago() {
        return this.getPagoNeto();
    }

}
