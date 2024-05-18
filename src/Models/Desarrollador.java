package Models;

import java.util.Queue;

import Models.Enum.Status;
import Repository.State;

import java.util.Date;
import java.util.LinkedList;

public class Desarrollador {

    public double TPS;
    private Ticket lastTicket;
    private Queue<Ticket> Resueltos; // Cola tickets resueltos
    private State simulacion;
    
    public Desarrollador() {
        this.Resueltos = new LinkedList<Ticket>();
        this.TPS = 0.0;
        this.simulacion = State.getInstance();
    }

    public void tomarTicket(Ticket ticket, double nuevoTPS) {
        lastTicket.setStatus(Status.CLOSED);
        ticket.setResolutionDate(new Date());
        Resueltos.add(lastTicket);

        TPS = nuevoTPS;

        // TODO: Revisar si estar fechas estan bien, las puso copilot
        ticket.setStatus(Status.IN_PROGRESS);
        ticket.setUpdatedDate(new Date());
        ticket.setLastViewedDate(new Date());
        lastTicket = ticket;
    }

}
