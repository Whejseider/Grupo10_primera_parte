package modelo;

public class MovilSeguimiento extends DecoratorAlarma {

    public MovilSeguimiento(ISistemaAlarma alarma) {
        super.setAlarma(alarma);
    }

    @Override
    public double getPrecio() {
        return getAlarma().getPrecio() + valorAgregarMovilSeguimiento;
    }

    @Override
    public String getDetalles() {
        return getAlarma().getDetalles() + " Movil de Acompañamiento";
    }
}