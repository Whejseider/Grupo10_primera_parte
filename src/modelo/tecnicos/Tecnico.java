package modelo.tecnicos;

import modelo.interfaces.IAbonado;

/**
 * Clase que representa a un tecnico del sistema
 */
public class Tecnico implements ITecnico {
    private static int numero = 0;
    private final String nombre;
    private boolean disponible = true;
    private final int id;

    /**
     * Constructor de la clase tecnico
     * @param nombre El nombre a usar
     */
    public Tecnico(String nombre) {
        this.nombre = nombre;
        this.id = numero++;
    }

    /**
     * Obtiene el id del tecnico
     * @return El id del tecnico
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del técnico
     * @return El nombre del técnico
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la disponibilidad del tecnico.
     * @return Verdadero si no está realizando ningún service
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Sobreescribe el estado disponible del tecnico. SOLO PARA SERIALIZACION.
     * @param disponible El nuevo estado.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Asigna un tecnico. Mientras esté asignado, no se lo puede llamar a otros service. Si
     * el tecnico ya estaba asignado, espera hasta que sea liberado para asignarse.
     * @param abonado El abonado a asignar.
     */
    public synchronized void asignarAbonado(IAbonado abonado) throws InterruptedException {
        while (!disponible) {
            wait();
        }

        this.disponible = false;
        notifyAll();
    }

    /**
     * Libera el tecnico de sus funciones, y vuelve a estar disponible.
     */
    public synchronized void liberar() {
        this.disponible = true;
        notifyAll();
    }
}
