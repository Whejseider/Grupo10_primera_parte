package modelo.tecnicos;

import modelo.interfaces.IAbonado;

import java.util.Observable;

/**
 * Clase que representa un servicio tecnico realizado o a realizar
 * de un abonado.
 */
public class ServicioTecnico extends Observable implements Runnable {
    private final IAbonado abonado;
    private final ITecnico tecnico;
    private int progreso = 0;
    private IEstadoServicioTecnico estado;
    private final int intervaloProgreso = 10;

    /**
     * Constructor de la clase ServicioTecnico.
     * @param abonado El abonado a asignar.
     * @param tecnico El tecnico a asignar.
     */
    public ServicioTecnico(IAbonado abonado, ITecnico tecnico) {
        this.abonado = abonado;
        this.tecnico = tecnico;
        this.estado = new EstadoServicioEsperandoTecnico(this);
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

        this.estado = new EstadoServicioFinalizado(this);
        this.progreso = 0;

        setChanged();
        notifyObservers(100);
    }

    public void setEstado(IEstadoServicioTecnico estado) {
        this.estado = estado;
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
    public ITecnico getTecnico() {
        return tecnico;
    }

    /**
     * Devuelve el estado actual del servicio
     */
    public IEstadoServicioTecnico getEstado() {
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
        this.estado = new EstadoServicioEsperandoTecnico(this);
        try {
            this.tecnico.asignarAbonado(abonado);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.estado = new EstadoServicioEnCurso(this);
        this.avanzar();
        this.tecnico.liberar();
    }
}
