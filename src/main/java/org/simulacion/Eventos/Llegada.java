package org.simulacion.Eventos;
import java.util.ArrayList;

import org.simulacion.FDPS.IA;
import org.simulacion.FDPS.TR;
import org.simulacion.FDPS.PT;
import org.simulacion.Models.Simulacion;
import org.simulacion.Models.Ticket;
import org.simulacion.Models.Enum.Priority;
import org.simulacion.Models.Enum.Status;

public class Llegada extends Evento {

    public Llegada(double instante) {
        super(instante, "Llegada");
    }

    @Override
    public void actualizarVectorDeEstado() {
        try{
            Ticket nuevoTicket = new Ticket();
            nuevoTicket.setCreatedTime(Simulacion.getInstance().getTiempoActual());
            nuevoTicket.setStatus(Status.OPEN);
            nuevoTicket.setVotes(Math.ceil(PT.calculate()));

            // TODO: Elegir un criterio para asignar prioridad, por ejemplo: ver el % de tickets de cada status en el dataset
            double random = Math.random();
            Priority priority = (random < 0.021)? Priority.HIGHEST : (random < (0.0336+0.021)) ? Priority.HIGH : (random < (0.0252+0.0336+0.021)) ? Priority.MEDIUM : Priority.LOW;
            
            nuevoTicket.setPriority(priority);
            Priority.getQueueByPriority(priority).add(nuevoTicket);
            Simulacion.getInstance().addNT();
        }catch (Exception e){}
    }

    @Override
    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        try{
            double intervaloArribos = IA.calculate();
            double tiempoActual = Simulacion.getInstance().getTiempoActual();
            Llegada nuevaLlegada = new Llegada(tiempoActual + intervaloArribos);
            return new ArrayList<Evento>() {{
                add(nuevaLlegada);
            }};
        }catch (Exception e){

        }
        return new ArrayList<Evento>();
    }

    @Override
    public ArrayList<Evento> eventosFuturosCondicionados() {
        // Si pasa esto debo generar una nueva salida
        try{
            if(Simulacion.getInstance().sumNs() <= Simulacion.getInstance().getVariableControl()) {
                double tiempoResolucion = TR.calculate();
                return new ArrayList<Evento>() {{
                    add(new Salida(getInstante() + tiempoResolucion));
                }};
            }
        }catch (Exception ex){

        }
        return new ArrayList<Evento>();
    }
    
}
