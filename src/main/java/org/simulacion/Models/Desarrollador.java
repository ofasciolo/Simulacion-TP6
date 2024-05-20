package org.simulacion.Models;

import java.util.Objects;
import java.util.Queue;

import org.simulacion.Models.Enum.Status;

import java.util.LinkedList;
import java.util.List;

public class Desarrollador {

    public double TPS;
    private Ticket lastTicket;
    private List<Ticket> Resueltos; // Cola tickets resueltos
    
    public Desarrollador() {
        this.Resueltos = new LinkedList<Ticket>();
        this.TPS = 0.0;
    }

    public List<Ticket> getResueltos() {
        return Resueltos;
    }

    public void tomarTicket(Ticket ticket, double nuevoTPS) {
        double tiempoActual = Simulacion.getInstance().getTiempoActual();
        if(Objects.nonNull(lastTicket)){

            lastTicket.setStatus(Status.CLOSED);
            lastTicket.setResolvedTime(tiempoActual);
            Resueltos.add(lastTicket);

            // Update sps
            double tiempoPermanencia = lastTicket.getResolvedTime() - lastTicket.getCreatedTime();
            double sps = Simulacion.getInstance().getSPS() + tiempoPermanencia;
            Simulacion.getInstance().setSPS(sps);
        }

        TPS = nuevoTPS;

        ticket.setStatus(Status.IN_PROGRESS);
        ticket.setInProgressTime(tiempoActual);
        lastTicket = ticket;
    }

}
