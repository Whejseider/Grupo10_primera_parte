package modelo.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modelo.interfaces.IPromocion;

public class PromocionOutput {
    private FileOutputStream file;
    private ObjectOutputStream output;

    public void abrir() throws IOException {
        file = new FileOutputStream("promocion.dat");
        output = new ObjectOutputStream(file);
    }

    public void cerrar() throws IOException {
        if (output != null)
            output.close();
    }

    public void escribir(IPromocion promocion) throws IOException {
        if (output != null)
            output.writeObject(promocion);
    }

}
