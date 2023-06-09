package modelo;

import modelo.interfaces.IAbonado;

public class Tecnico extends Thread {
    private String nombre;
    private IAbonado abonado;
    
    /**
     * Constructor de la clase tecnico.
     * 
     * <b>pre: </b>: El nombre tiene que estar definidos y no ser vacío
     * 
     * @param nombre El nombre del tecnico a crear
     */
    public Tecnico(String nombre) {
        this.nombre = nombre;
    }
    
    
    /**
     * Obtiene el nombre del tecnico
     * 
     * @return El nombre del tecnico
     */
    public String getNombre() {
        return this.nombre;
    }
    
    @Override
    public void run() {
    	Sistema sistema= Sistema.getInstance();
        while (true) {
            abonado=sistema.asignarAbonado(this);
            this.visitarAbonado( abonado);
        }
    }
    
    /**
     * visita un tecnico a un abonado
     * @param abonado
     */
    public void visitarAbonado(IAbonado abonado) {
        System.out.println("Técnico " + this.getNombre() + " visitando a " + abonado.getNombre());
        try {
            this.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Técnico " + this.getNombre() + " ha terminado la visita a " + abonado.getNombre());
    }
}
