package persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectFileOutput<T> {
    private FileOutputStream file;
    private ObjectOutputStream output;
    private final String nombreArchivo;

    public ObjectFileOutput(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void abrir() throws IOException {
        file = new FileOutputStream(nombreArchivo);
        output = new ObjectOutputStream(file);
    }

    public void cerrar() throws IOException {
        if (output != null)
            output.close();
    }

    public void escribir(T objeto) throws IOException {
        if (output != null)
            output.writeObject(objeto);
    }
}
