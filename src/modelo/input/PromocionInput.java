package modelo.input;

import modelo.interfaces.IPromocion;

public class PromocionInput extends ObjectFileInput<IPromocion> {
    public PromocionInput() {
        super("promocion.dat");
    }
}