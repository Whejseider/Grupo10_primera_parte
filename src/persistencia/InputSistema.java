package persistencia;

/**
 * Clase utilidad para escribir el sistema a un archivo binario.
 */
public class InputSistema extends ObjectFileInput<SistemaDTO> {
    public InputSistema() {
        super("sistema.dat");
    }
}
