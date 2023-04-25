package modelo.alarma;

public class ServicioAlarmaVivienda extends ServicioAlarma {
    @Override
    public double getPrecio() {
        return precioBaseVivienda;
    }

    @Override
    public String getTipoAlarma() {
        return "Servicio de Monitoreo de Alarmas de Viviendas";
    }


}
