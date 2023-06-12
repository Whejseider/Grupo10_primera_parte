package persistencia;

/**
 * Clase utilidad para escribir el sistema a un archivo binario.
 */
public class OutputSistema extends ObjectFileOutput<SistemaDTO> {
    public OutputSistema() {
        super("sistema.dat");
    }
}
