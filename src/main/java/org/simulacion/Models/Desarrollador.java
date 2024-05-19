package org.simulacion.Models;

import java.util.Objects;
import java.util.Queue;

import org.simulacion.Models.Enum.Status;

import java.util.Date;
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
        if(Objects.nonNull(lastTicket)){
            lastTicket.setStatus(Status.CLOSED);
            ticket.setUpdatedDate(new Date());
            ticket.setLastViewedDate(new Date());
            ticket.setResolutionDate(new Date());
            Resueltos.add(lastTicket);
        }

        TPS = nuevoTPS;

        // TODO: Revisar si estar fechas estan bien, las puso copilot
        ticket.setStatus(Status.IN_PROGRESS);
        ticket.setUpdatedDate(new Date());
        ticket.setLastViewedDate(new Date());
        ticket.setFirstResponseDate(new Date());
        lastTicket = ticket;
    }

}
