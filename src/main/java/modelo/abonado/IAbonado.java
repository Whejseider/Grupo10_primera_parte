package modelo.abonado;

import modelo.contrato.IContrato;
import modelo.domicilio.Domicilio;

import java.util.List;

public interface IAbonado extends Cloneable {
    double porcBonificacionJuridica = 0.5;


    String getNombre();

    String getDni();

    List<IContrato> getContratos();

    List<Domicilio> getDomicilios();

    double getPagoDeServicio(IContrato contrato, int i);

    void agregarContrato(IContrato contrato);

    void eliminarContrato(IContrato contrato);

    void agregarDomicilio(Domicilio domicilio);

    void eliminarDomicilio(Domicilio domicilio);

    String getTipo();

    public Object clone() throws CloneNotSupportedException;
}
