package modelo.alarma;

public abstract class ServicioAlarma implements IServicioAlarma {
    public ServicioAlarma() {

    }

    @Override
    public abstract double getPrecio();

    @Override
    public Object clone()  {
        Object serviceClon = null;
        try {
            serviceClon = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return serviceClon;
    }
}
