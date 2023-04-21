package modelo;

public class SistemaAlarmaVivienda implements ISistemaAlarma{

    private double precioBase;

    /**
     * Constuctor Alarma para vivienda<br>
     * <br>Precio base = 8500</br>
     */
    public SistemaAlarmaVivienda() {
        this.precioBase = 8500;
    }

    @Override
    public double getPrecio() {
        return this.precioBase;
    }


    @Override
    public String getDetalles() {
        return null;
    }


    //Getters & Setters
    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
}
