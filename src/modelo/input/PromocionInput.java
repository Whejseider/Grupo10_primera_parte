package modelo.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import modelo.interfaces.IPromocion;

public class PromocionInput {
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

    public IPromocion leer() throws IOException {
        IPromocion promocion = null;
        if (input != null)
            try {
                promocion = (IPromocion) input.readObject();
            } catch (Exception e) {
            }
        return promocion;
    }

}