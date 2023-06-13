package modelo.tecnicos;

/**
 * Clase para el estado finalizado del servicio tecnico
 */
public class EstadoServicioFinalizado implements IEstadoServicioTecnico {
    private final ServicioTecnico servicioTecnico;

    public EstadoServicioFinalizado(ServicioTecnico servicio) {
        this.servicioTecnico = servicio;
    }

    public String getTextoEstado() {
        return "Service finalizado";
    }

    @Override
    public boolean isEnCurso() {
        return false;
    }

    @Override
    public boolean isEsperandoTecnico() {
        return false;
    }

    @Override
    public boolean isFinalizado() {
        return true;
    }
}
