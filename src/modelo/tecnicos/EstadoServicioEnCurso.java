package modelo.tecnicos;

/**
 * Clase para el estado en curso del servicio tecnico
 */
public class EstadoServicioEnCurso implements IEstadoServicioTecnico {
    private final ServicioTecnico servicioTecnico;

    public EstadoServicioEnCurso(ServicioTecnico servicio) {
        this.servicioTecnico = servicio;
    }

    public String getTextoEstado() {
        return this.servicioTecnico.getTecnico().getNombre() + " está trabajando";
    }

    @Override
    public boolean isEnCurso() {
        return true;
    }

    @Override
    public boolean isEsperandoTecnico() {
        return false;
    }

    @Override
    public boolean isFinalizado() {
        return false;
    }
}
