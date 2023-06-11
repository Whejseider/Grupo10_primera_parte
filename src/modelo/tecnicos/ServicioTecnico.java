package modelo.tecnicos;

import modelo.interfaces.IAbonado;

import java.util.Observable;

/**
 * Clase que representa un servicio tecnico realizado o a realizar
 * de un abonado.
 */
public class ServicioTecnico extends Observable implements Runnable {
    private final IAbonado abonado;
    private final Tecnico tecnico;
    private int progreso = 0;
    private final int intervaloProgreso = 10;
    private Estado estado = Estado.ESPERANDO_TECNICO;

    /**
     * El estado actual del servicio
     */
    public enum Estado {
        ESPERANDO_TECNICO,
        EN_CURSO,
        FINALIZADO
    }

    /**
     * Constructor de la clase ServicioTecnico.
     * @param abonado El abonado a asignar.
     * @param tecnico El tecnico a asignar.
     */
    public ServicioTecnico(IAbonado abonado, Tecnico tecnico) {
        this.abonado = abonado;
        this.tecnico = tecnico;
    }

    /**
     * Algoritmo que simula el avance del service. Notifica los progresos
     * a sus observers.
     */
    private void avanzar() {

        while (progreso < 100) {
            setChanged();
            notifyObservers(progreso);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.progreso += intervaloProgreso;
        }

        this.estado = Estado.FINALIZADO;
        this.progreso = 0;

        setChanged();
        notifyObservers(100);
    }

    /**
     * @return Verdadero si el service ha finalizado.
     */
    public boolean getTerminado() {
        return this.progreso == 100;
    }

    /**
     * Obtiene el progreso actual del service.
     * @return Un numero entre 0 y 100 que indica el avance del service.
     */
    public int getProgreso() {
        return progreso;
    }

    /**
     * Obtiene el abonado asignado al service
     */
    public IAbonado getAbonado() {
        return abonado;
    }

    /**
     * Obtiene el tecnico asignado al service
     */
    public Tecnico getTecnico() {
        return tecnico;
    }

    /**
     * Obtiene texto con información del estado actual del servicio.
     * @return Una string para mostrar en pantalla.
     */
    public String getTextoEstado() {
        switch (this.estado) {
            case EN_CURSO:
                return tecnico.getNombre() + " está trabajando";
            case ESPERANDO_TECNICO:
                return "Esperando la llegada del técnico";
            case FINALIZADO:
                return "Service finalizado";
            default:
                return "Servicio sin iniciar";
        }
    }

    /**
     * Devuelve el estado actual del servicio
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Inicia el servicio. Intenta asignar el abonado al tecnico.
     *
     * - Si el técnico está disponible, se pone a trabajar inmediatamente.
     * - Si el técnico no está disponible, se espera a que finalice su servicio actual y pendientes
     * para asignarse.
     */
    @Override
    public void run() {
        this.estado = Estado.ESPERANDO_TECNICO;
        try {
            this.tecnico.asignarAbonado(abonado);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.estado = Estado.EN_CURSO;
        this.avanzar();
        this.tecnico.liberar();
    }
}
