package modelo.alarma;

public abstract class SistemaAlarma implements ISistemaAlarma {
    private int cantCamaras;
    private int cantBtnAntiPanico;
    private boolean tieneMovilSeguimiento;

    public SistemaAlarma() {
        this.cantCamaras = 0;
        this.cantBtnAntiPanico = 0;
        this.tieneMovilSeguimiento = false;
    }

    public int getCantCamaras() {
        return cantCamaras;
    }

    public void setCantCamaras(int cantCamaras) {
        this.cantCamaras = cantCamaras;
    }

    public int getCantBtnAntiPanico() {
        return cantBtnAntiPanico;
    }

    public void setCantBtnAntiPanico(int cantBtnAntiPanico) {
        this.cantBtnAntiPanico = cantBtnAntiPanico;
    }

    public boolean isTieneMovilSeguimiento() {
        return tieneMovilSeguimiento;
    }

    public void setTieneMovilSeguimiento(boolean tieneMovilSeguimiento) {
        this.tieneMovilSeguimiento = tieneMovilSeguimiento;
    }

}
