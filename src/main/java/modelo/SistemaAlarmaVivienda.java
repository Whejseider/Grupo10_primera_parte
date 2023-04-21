package modelo;

public class SistemaAlarmaVivienda extends SistemaAlarma {

    private double precioBase;
    private double precioTotal;

    public SistemaAlarmaVivienda() {
        this.precioBase = 8500;
        this.precioTotal = this.precioBase;
    }

    @Override
    public double getPrecio() {
        return this.precioTotal;
    }

    @Override
    public void agregarCamara(int cantidad) {
        super.setCantCamaras(cantidad);
        this.precioTotal += valorAgregarCamara * cantidad;

    }

    @Override
    public void agregarBtnAntiPanico(int cantidad) {
        super.setCantBtnAntiPanico(cantidad);
        this.precioTotal += valorAgregarBtnAntiPanico * cantidad;

    }

    @Override
    public void agregarMovilSeguimiento() {
        super.setTieneMovilSeguimiento(true);
        this.precioTotal += valorAgregarMovilSeguimiento;
    }

    @Override
    public String getDetalles() {
        return "[Sistema de Alarma de Vivienda]\n" +
                "Camaras = " + super.getCantCamaras() +
                "\nBotones de Arrepentimiento = " + super.getCantBtnAntiPanico() +
                "\nMovil de seguimiento = " + (super.isTieneMovilSeguimiento()? "Tiene movil de seguimiento" : "No tiene movil de seguimiento") +
                "\nPrecio Base = " + getPrecioBase() +
                "\nPrecio Total = " + getPrecioTotal();
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
