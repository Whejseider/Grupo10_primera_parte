package negocio.abonado;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.interfaces.IAbonado;
import negocio.interfaces.IContrato;
import negocio.interfaces.IPromocion;

public abstract class Abonado implements IAbonado {
    private String nombre;
    private String dni;
    private ArrayList<IContrato> contratos;

    public Abonado(String nombre, String dni) {
        assert (nombre != null);
        assert (dni != null);
        this.nombre = nombre;
        this.dni = dni;
        this.contratos = new ArrayList<IContrato>();
    }

    public void agregaContrato(IContrato contrato) {
        assert (contrato != null);
        this.contratos.add(contrato);
    }

    @Override
    public String getDetalle() {
        return null;
    }

    public ArrayList<IContrato> getContratos() {
        return contratos;
    }

    @Override
    public double getPagoNeto(IPromocion promo) {
        ArrayList<IContrato> contratos = getContratos();
        IContrato contrato;
        Iterator<IContrato> iterator = contratos.iterator();
        Double pagoNeto = 0.0;
        while (iterator.hasNext()) {
            contrato = iterator.next();
            pagoNeto = pagoNeto + contrato.getPrecio(promo);
        }
        return pagoNeto;
    }

    @Override
    public int cantidadDeContratos() {
        return this.contratos.size();
    }

    //    Revisar si es asi para clonar un arrayList, busque y es lo unico que encontre
//    El profe dijo que nunca hay que usar new adentro de un clon
//        -Fran V
    @Override
    public Object clone() throws CloneNotSupportedException {
        Abonado abonadoClon = null;
        try {
            abonadoClon = (Abonado) super.clone();
            abonadoClon.contratos = new ArrayList<>(contratos.size());
            for (IContrato contrato : contratos) {
                abonadoClon.contratos.add((IContrato) contrato.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return abonadoClon;
    }

}
