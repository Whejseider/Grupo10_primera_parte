package modelo.tecnicos;

import java.io.Serializable;

import modelo.interfaces.IAbonado;

public class Tecnico implements Serializable {
    private static int numero = 0;
    private final String nombre;
    private boolean disponible = true;
    private final int id;

    public Tecnico(String nombre) {
        this.nombre = nombre;
        this.id = numero++;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public synchronized void asignarAbonado(IAbonado abonado) throws InterruptedException {
        while (!disponible) {
            wait();
        }

        System.out.println("DISPONIBLE EN FALSE!!!");

        this.disponible = false;
        notifyAll();
    }

    public synchronized void liberar() {
        this.disponible = true;
        notifyAll();
    }
}
