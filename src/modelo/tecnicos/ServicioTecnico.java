package modelo.tecnicos;

import modelo.interfaces.IAbonado;

import java.util.Observable;

public class ServicioTecnico extends Observable implements Runnable {
    private final IAbonado abonado;
    private final Tecnico tecnico;
    private int progreso = 0;
    private Estado estado = Estado.ESPERANDO_TECNICO;

    public enum Estado {
        ESPERANDO_TECNICO,
        EN_CURSO,
        FINALIZADO
    }

    public ServicioTecnico(IAbonado abonado, Tecnico tecnico) {
        this.abonado = abonado;
        this.tecnico = tecnico;
    }

    private void avanzar() {
        for (int i = 1; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.progreso = i * 10;
            setChanged();
            notifyObservers(progreso);
        }

        this.estado = Estado.FINALIZADO;
        this.progreso = 0;

        setChanged();
        notifyObservers(100);
    }

    public boolean getTerminado() {
        return this.progreso == 100;
    }

    public int getProgreso() {
        return progreso;
    }

    public IAbonado getAbonado() {
        return abonado;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

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

    public Estado getEstado() {
        return estado;
    }

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
