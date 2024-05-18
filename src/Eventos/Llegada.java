package Eventos;
import java.util.ArrayList;
import java.util.Date;

import Models.Ticket;
import Models.Enum.Priority;
import Models.Enum.Status;

public class Llegada extends Evento {

    public Llegada(double instante) {
        super(instante);
    }

    @Override
    public void determinarEvento() {
        Ticket nuevoTicket = new Ticket();
        nuevoTicket.setCreatedDate(new Date());
        nuevoTicket.setUpdatedDate(new Date());
        nuevoTicket.setLastViewedDate(new Date());
        nuevoTicket.setResolutionDate(new Date());
        nuevoTicket.setStatus(Status.OPEN);
        // TODO: Cargar la estimacion del ticket con una FDP
        // nuevoTicket.setVotes();

        // TODO: Elegir un criterio para asignar prioridad, por ejemplo: ver el % de tickets de cada status en el dataset
        double random = Math.random();
        Priority priority = (random < 0.3) ? Priority.HIGH : (random < 0.6) ? Priority.MEDIUM : Priority.LOW;
        
        nuevoTicket.setPriority(priority);
        simulacion.getByPriority(priority).add(nuevoTicket);
    }

    @Override
    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        double IA = FDPS.IA.calculate();
        Llegada nuevaLlegada = new Llegada(instante + IA);
        return new ArrayList<Evento>() {{
            add(nuevaLlegada);
        }};
    }

    @Override
    public ArrayList<Evento> eventosFuturosCondicionados() {
        // Si pasa esto debo generar una nueva salida
        if(simulacion.sumNs() <= simulacion.getN()) { 
            double TA = FDPS.TA.calculate();
            return new ArrayList<Evento>() {{
                add(new Salida(instante + TA));
            }};
        }
        return new ArrayList<Evento>();
    }
    
}
