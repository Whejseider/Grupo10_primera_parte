package negocio;

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

    protected Iterator<IContrato> getIteratorContratos() {
        return this.getContratos().iterator();
    }

    @Override
    public double getPagoNeto(IPromocion promo) {
        IContrato contrato;
        Iterator<IContrato> iterator = getIteratorContratos();
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

    @Override
    public IAbonado clone() throws CloneNotSupportedException {
        Abonado abonadoClonado = (Abonado) super.clone();
        abonadoClonado.contratos.clear();

        Iterator<IContrato> iterator = this.getIteratorContratos();

        while (iterator.hasNext()) {
            IContrato contrato = (IContrato) iterator.next().clone();
            abonadoClonado.contratos.add(contrato);
        }

        return abonadoClonado;
    }
}
