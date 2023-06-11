package persistencia;

import modelo.SinPromocion;
import modelo.Sistema;
import modelo.interfaces.IAbonado;
import modelo.interfaces.IPromocion;
import modelo.tecnicos.Tecnico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaDTO implements Serializable {
    private ArrayList<IAbonado> abonados = new ArrayList<IAbonado>();
    private ArrayList<Tecnico> tecnicos = new ArrayList<Tecnico>();
    private IPromocion promocionActiva = new SinPromocion();
    private LocalDate fecha;

    public SistemaDTO() {

    }

    public ArrayList<IAbonado> getAbonados() {
        return abonados;
    }

    public void setAbonados(ArrayList<IAbonado> abonados) {
        this.abonados = abonados;
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public IPromocion getPromocionActiva() {
        return promocionActiva;
    }

    public void setPromocionActiva(IPromocion promocionActiva) {
        this.promocionActiva = promocionActiva;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
