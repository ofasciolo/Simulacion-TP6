package org.simulacion.Eventos;

import java.util.ArrayList;

public abstract class Evento {
    private double instante;
    private String nombre;

    public Evento(double instante, String nombre) {
        this.instante = instante;
        this.nombre = nombre;
    }
    public double getInstante(){
        return instante;
    }
    public abstract void actualizarVectorDeEstado();
    public abstract ArrayList<Evento> eventosFuturosNoCondicionados();
    public abstract ArrayList<Evento> eventosFuturosCondicionados();

    @Override
    public String toString(){
        return nombre;
    }
}