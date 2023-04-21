package modelo;

public class Camara extends DecoratorAlarma {

    int cantCamaras;

    public Camara(ISistemaAlarma alarma, int cantCamaras) {
        super.setAlarma(alarma);
        this.cantCamaras = cantCamaras;
    }

    @Override
    public double getPrecio() {
        return getAlarma().getPrecio() + (valorAgregarCamara * this.cantCamaras);
    }

    @Override
    public String getDetalles() {
        return getAlarma().getDetalles() + " +[" + this.cantCamaras + "] Camaras";
    }

}
