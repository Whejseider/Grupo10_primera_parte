package modelo.alarma;

public interface IServicioAlarma extends Cloneable{
    double precioBaseVivienda = 8500;
    double precioBaseComercio = 10000;

    double getPrecio();

    String getTipoAlarma();

    public Object clone();
}
