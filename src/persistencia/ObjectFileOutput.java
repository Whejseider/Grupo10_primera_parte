package persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Clase generica para depersistir objetos con archivos binarios
 * @param <T> El tipo de dato a leer
 */
public class ObjectFileOutput<T> {
    private FileOutputStream file;
    private ObjectOutputStream output;
    private final String nombreArchivo;

    public ObjectFileOutput(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Abre el archivo para su escritura.
     */
    public void abrir() throws IOException {
        file = new FileOutputStream(nombreArchivo);
        output = new ObjectOutputStream(file);
    }

    /**
     * Cierra el archivo
     */
    public void cerrar() throws IOException {
        if (output != null)
            output.close();
    }

    /**
     * Escribe el archivo
     * @param objeto El objeto con el cual escribir
     */
    public void escribir(T objeto) throws IOException {
        if (output != null)
            output.writeObject(objeto);
    }
}
