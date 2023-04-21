package modelo;

public interface ISistemaAlarma {
    double valorAgregarCamara = 3000;
    double valorAgregarBtnAntiPanico = 2000;
    double valorAgregarMovilSeguimiento = 7500;

    double getPrecio();
    double agregarCamara();
    double agregarBtnAntiPanico();
    double agregarMovilSeguimiento();
    String getDetalles();
}
