package modelo.tecnicos;

/**
 * Factory para tecnicos
 */
public class TecnicoFactory {
    /**
     * Obtiene un nuevo tecnico
     * @param nombre El nombre a utilizar
     * @return
     */
    public static Tecnico getTecnico(String nombre) {
        return new Tecnico(nombre);
    }
}
