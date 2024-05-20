package org.simulacion.Eventos;

import org.simulacion.Models.Simulacion;
import org.simulacion.Models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Actualizacion extends Evento {

    public Actualizacion(double instante) {
        super(instante, "Actualizacion");
    }

    @Override
    public void actualizarVectorDeEstado() {
        Simulacion simulacion = Simulacion.getInstance();
        // Creo que es el metodo mas complicado, 
        // tiene que elegir un criterio para actualizar los tickets
        // recorrer todos los tickets e ir sacando unos de una cola y metiendolos en otra
        // Por ejemplo: si el tiempo entre que el ticket fue creado y el instante actual es mayor al tiempo de resolucion estimado, se cambia de cola
        List<Ticket> eventosLowViejos = simulacion.getTicketsViejos(simulacion.getNSL());
        List<Ticket> eventosMediumViejos = simulacion.getTicketsViejos(simulacion.getNSM());
        List<Ticket> eventosHighViejos = simulacion.getTicketsViejos(simulacion.getNSH());

        simulacion.sacarLowTickets(eventosLowViejos);
        simulacion.sacarMediumTickets(eventosMediumViejos);
        simulacion.sacarHighTickets(eventosHighViejos);

        simulacion.getNSM().addAll(eventosLowViejos);
        simulacion.getNSH().addAll(eventosMediumViejos);
        simulacion.getNSHT().addAll(eventosHighViejos);
    }

    @Override
    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        double tiempoActual = Simulacion.getInstance().getTiempoActual();
        Actualizacion nuevActualizacion = new Actualizacion(tiempoActual + Simulacion.getInstance().getTiempoDeActualizacion());
        return new ArrayList<Evento>() {{
            add(nuevActualizacion);
        }};
    }

    @Override
    public ArrayList<Evento> eventosFuturosCondicionados() {
        return new ArrayList<Evento>();
    }
    
}
