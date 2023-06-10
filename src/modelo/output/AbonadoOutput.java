package modelo.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modelo.Abonado;

public class AbonadoOutput {
    private FileOutputStream file;
    private ObjectOutputStream output;

    public void abrir() throws IOException {
        file = new FileOutputStream("abonados.dat");
        output = new ObjectOutputStream(file);
    }

    public void cerrar() throws IOException {
        if (output != null)
            output.close();
    }

    public void escribir(Abonado abonado) throws IOException {
        if (output != null)
            output.writeObject(abonado);
    }

}
