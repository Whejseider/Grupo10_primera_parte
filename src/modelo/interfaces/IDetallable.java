package modelo.interfaces;

import java.io.Serializable;

public interface IDetallable extends Serializable {
	public String getDetalle(IPromocion promo);
}
