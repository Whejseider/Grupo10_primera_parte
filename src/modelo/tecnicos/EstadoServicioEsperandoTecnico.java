package modelo.tecnicos;

public class EstadoServicioEsperandoTecnico implements IEstadoServicioTecnico {
    private final ServicioTecnico servicioTecnico;

    public EstadoServicioEsperandoTecnico(ServicioTecnico servicio) {
        this.servicioTecnico = servicio;
    }

    public String getTextoEstado() {
        return "Esperando la llegada del técnico";
    }

    @Override
    public boolean isEnCurso() {
        return false;
    }

    @Override
    public boolean isEsperandoTecnico() {
        return true;
    }

    @Override
    public boolean isFinalizado() {
        return false;
    }
}
