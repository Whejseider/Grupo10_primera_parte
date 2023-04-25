package modelo.alarma;

public interface ISistemaAlarma {
    double valorAgregarCamara = 3000;
    double valorAgregarBtnAntiPanico = 2000;
    double valorAgregarMovilSeguimiento = 7500;

    double getPrecio();

    void agregarCamara(int cantidad);
    void agregarBtnAntiPanico(int cantidad);
    void agregarMovilSeguimiento();

    String getDetalles();
}
