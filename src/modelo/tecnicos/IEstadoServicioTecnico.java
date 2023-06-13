package modelo.tecnicos;

public interface IEstadoServicioTecnico {
    String getTextoEstado();

    boolean isEnCurso();

    boolean isEsperandoTecnico();

    boolean isFinalizado();
}
