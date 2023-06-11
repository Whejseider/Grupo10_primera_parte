package modelo.output;

import modelo.interfaces.IPromocion;

public class PromocionOutput extends ObjectFileOutput<IPromocion> {
    public PromocionOutput() {
        super("promocion.dat");
    }
}
