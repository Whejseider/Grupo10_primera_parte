package modelo;

public class BotonAntiPanico extends DecoratorAlarma {
    int cantBotones;

    public BotonAntiPanico(ISistemaAlarma alarma, int cantBotones) {
        super.setAlarma(alarma);
        this.cantBotones = cantBotones;
    }

    @Override
    public double getPrecio() {
        return getAlarma().getPrecio() + (valorAgregarBtnAntiPanico * this.cantBotones);
    }

    @Override
    public String getDetalles() {
        return getAlarma().getDetalles() + " +[" + this.cantBotones + "] Botones AntiPanico";
    }
}
