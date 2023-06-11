package modelo.tecnicos;

import java.io.Serializable;

import modelo.interfaces.IAbonado;

public class Tecnico implements Serializable {
    private static int numero = 0;
    private final String nombre;
    private boolean disponible;
    private final int id;

    public Tecnico(String nombre) {
        this.nombre = nombre;
        this.disponible = true;
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

    public synchronized void asignarAbonado(IAbonado abonado) throws InterruptedException {
        while (!disponible) {
            wait();
        }

        this.disponible = false;
        notifyAll();
    }

    public synchronized void liberar() {
        this.disponible = true;
        notifyAll();
    }
}
