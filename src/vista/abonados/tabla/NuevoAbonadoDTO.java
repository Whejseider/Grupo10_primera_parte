package vista.abonados.tabla;

public class NuevoAbonadoDTO {
    private String nombre;
    private String dni;
    private String tipo;
    
    public NuevoAbonadoDTO(String tipo, String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
    
    public String getTipo() {
        return tipo;
    }
}
