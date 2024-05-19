package org.simulacion.Models;

import java.util.Objects;
import java.util.Queue;

import org.simulacion.Models.Enum.Status;

import java.util.LinkedList;

public class Desarrollador {

    public double TPS;
    private Ticket lastTicket;
    private Queue<Ticket> Resueltos; // Cola tickets resueltos
    
    public Desarrollador() {
        this.Resueltos = new LinkedList<Ticket>();
        this.TPS = 0.0;
    }

    public void tomarTicket(Ticket ticket, double nuevoTPS) {
        double tiempoActual = Simulacion.getInstance().getTiempoActual();
        if(Objects.nonNull(lastTicket)){

            lastTicket.setStatus(Status.CLOSED);
            ticket.setResolvedTime(tiempoActual);
            Resueltos.add(lastTicket);
        }

        TPS = nuevoTPS;

        ticket.setStatus(Status.IN_PROGRESS);
        ticket.setReadyTime(tiempoActual);
        lastTicket = ticket;
    }

}
