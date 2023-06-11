package modelo.input;

import modelo.interfaces.IAbonado;

import java.util.ArrayList;

public class AbonadoInput extends ObjectFileInput<ArrayList<IAbonado>> {
    public AbonadoInput() {
        super("abonados.dat");
    }
}
