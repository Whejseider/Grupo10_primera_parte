package negocio.factura;

import negocio.interfaces.IFactura;
import negocio.interfaces.IPromocion;

public class Factura implements IFactura {




    @Override
    public String getDetalle() {
        return null;
    }

    @Override
    public double getPagoNeto(IPromocion promo) {
        return 0;
    }

    @Override
    public double getPagoMedioDePago(IPromocion promo) {
        return 0;
    }
}
