package modelo.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import modelo.interfaces.IAbonado;

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

    public ArrayList<IAbonado> leer() throws IOException {
        ArrayList<IAbonado> abonados = null;
        if (input != null)
            try {
                abonados = (ArrayList<IAbonado>) input.readObject();
            } catch (Exception e) {
            }
        return abonados;
    }

}
