package org.simulacion.Eventos;

import java.util.ArrayList;

public abstract class Evento {
    private double instante;
    private String nombre;

    public Evento(double instante) {
        this.instante = instante;
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