package modelo.alarma;

public class ServicioAlarmaComercio extends ServicioAlarma {

    @Override
    public double getPrecio() {
        return precioBaseComercio;
    }

    @Override
    public String getTipoAlarma() {
        return "Servicio de Monitoreo de Alarmas de Comercios";
    }


}
