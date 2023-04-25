package modelo.abonado;

import modelo.contrato.IContrato;
import modelo.domicilio.Domicilio;

import java.util.List;

public interface IAbonado extends Cloneable {
    /**
     * Bonificacion que se realiza en el pago a partir del tercer serivicio<br>
     * si el abonado es de tipo Persona Juridica
     */
    double porcBonificacionJuridica = 0.5;

    String getNombre();

    void setNombre(String nombre);

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
