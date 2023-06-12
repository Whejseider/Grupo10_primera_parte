package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Clase generica para persistir objetos con archivos binarios
 * @param <T> El tipo de dato a guardar
 */
public class ObjectFileInput<T> {
    private FileInputStream file;
    private ObjectInputStream input;
    private final String nombreArchivo;

    /**
     * Constructor de ObjectFileInput.
     * @param nombreArchivo El nombre de archivo a utilizar
     */
    public ObjectFileInput(String nombreArchivo) {
         this.nombreArchivo = nombreArchivo;
    }

    /**
     * Abre el archivo para su lectura
     */
    public void abrir() throws IOException {
        file = new FileInputStream(this.nombreArchivo);
        input = new ObjectInputStream(file);
    }

    /**
     * Cierra el archivo
     */
    public void cerrar() throws IOException {
        if (input != null)
            input.close();
    }

    /**
     * Lee el contenido del archivo
     * @return El objeto leído.
     */
    public T leer() throws IOException {
        T objeto = null;
        if (input != null)
            try {
                objeto = (T) input.readObject();
            } catch (Exception e) {
            }

        return objeto;
    }
}
