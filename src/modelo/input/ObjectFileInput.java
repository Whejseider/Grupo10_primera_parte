package modelo.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectFileInput<T> {
    private FileInputStream file;
    private ObjectInputStream input;
    private final String nombreArchivo;

    public ObjectFileInput(String nombreArchivo) {
         this.nombreArchivo = nombreArchivo;
    }

    public void abrir() throws IOException {
        file = new FileInputStream(this.nombreArchivo);
        input = new ObjectInputStream(file);
    }

    public void cerrar() throws IOException {
        if (input != null)
            input.close();
    }

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
