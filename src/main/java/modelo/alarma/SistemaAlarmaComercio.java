package modelo.alarma;

public class SistemaAlarmaComercio extends SistemaAlarma {
    private double precioBase;
    private double precioTotal;

    public SistemaAlarmaComercio() {
        this.precioBase = 10000;
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
        return "[Sistema de Alarma de Comercios]\n" +
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
