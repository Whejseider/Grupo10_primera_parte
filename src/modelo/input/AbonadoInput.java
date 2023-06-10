package modelo.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import modelo.Abonado;

public class AbonadoInput {
    private FileInputStream file;
    private ObjectInputStream input;

    public void abrir() throws IOException {
        file = new FileInputStream("abonados.dat");
        input = new ObjectInputStream(file);
    }

    public void cerrar() throws IOException {
        if (input != null)
            input.close();
    }

    public Abonado leer() throws IOException {
        Abonado abonado = null;
        if (input != null)
            try {
                abonado = (Abonado) input.readObject();
            } catch (Exception e) {
            }
        return abonado;
    }

}
