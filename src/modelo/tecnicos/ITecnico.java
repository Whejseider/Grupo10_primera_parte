package modelo.tecnicos;

import modelo.interfaces.IAbonado;

import java.io.Serializable;

public interface ITecnico extends Serializable {
    void asignarAbonado(IAbonado abonado) throws InterruptedException;

    void liberar();

    boolean isDisponible();

    void setDisponible(boolean disponible);

    String getNombre();

    int getId();
}
