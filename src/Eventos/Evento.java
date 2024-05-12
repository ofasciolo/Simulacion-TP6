package Eventos;

import java.util.ArrayList;

import Repository.State;

public abstract class Evento {
    public double instante;
    protected String nombre;
    protected State simulacion;

    public Evento(double instante) {
        this.instante = instante;
        this.simulacion = State.getInstance();
    }
    public double getInstante(){
        return instante;
    }
    public abstract void determinarEvento();
    // public abstract void actualizarVariableEstado();
    public abstract ArrayList<Evento> eventosFuturosNoCondicionados();
    public abstract ArrayList<Evento> eventosFuturosCondicionados();

    @Override
    public String toString(){
        return nombre;
    }
}