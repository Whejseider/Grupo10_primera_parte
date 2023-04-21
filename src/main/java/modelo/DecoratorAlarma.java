package modelo;

public abstract class DecoratorAlarma implements ISistemaAlarma {
    private ISistemaAlarma alarma;

    public ISistemaAlarma getAlarma() {
        return alarma;
    }

    public void setAlarma(ISistemaAlarma alarma) {
        this.alarma = alarma;
    }
}
