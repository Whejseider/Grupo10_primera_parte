package vista.abonados.detalle.contratos;

public class NuevoContratoDTO {
    private String tipo;
    private String domicilio;
    private boolean tieneMovil;
    private int cantCamaras;
    private int cantBotones;
    
    public String getTipo() {
        return tipo;
    }
    
    public String getDomicilio() {
        return domicilio;
    }
    
    public boolean getTieneMovil() {
        return tieneMovil;
    }
    
    public int getCantCamaras() {
        return cantCamaras;
    }
    
    public int getCantBotones() {
        return cantBotones;
    }
    
    public NuevoContratoDTO(String tipo, String domicilio, boolean tieneMovil, int cantCamaras, int cantBotones) {
        this.tipo = tipo;
        this.domicilio = domicilio;
        this.tieneMovil = tieneMovil;
        this.cantCamaras = cantCamaras;
        this.cantBotones = cantBotones;
    }

}
