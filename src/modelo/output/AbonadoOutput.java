package modelo.output;

import java.util.ArrayList;

import modelo.interfaces.IAbonado;

public class AbonadoOutput extends ObjectFileOutput<ArrayList<IAbonado>> {
    public AbonadoOutput() {
        super("abonados.dat");
    }
}
