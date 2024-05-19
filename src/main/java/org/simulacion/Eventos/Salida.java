package org.simulacion.Eventos;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.simulacion.FDPS.TR;
import org.simulacion.Models.Desarrollador;
import org.simulacion.Models.Simulacion;
import org.simulacion.Models.Ticket;

public class Salida extends Evento {

    public Salida(double instante) {
        super(instante, "Salida");
    }

    @Override
    public void actualizarVectorDeEstado() {
        Desarrollador desarrollador = Simulacion.getInstance().getProximoDesarrolladorLibre();
        Ticket ticket = Simulacion.getInstance().getNextTicket();
        double tiempoActual = Simulacion.getInstance().getTiempoActual();
        desarrollador.tomarTicket(ticket, tiempoActual);
    }

    @Override
    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        return new ArrayList<Evento>();
    }

    @Override
    public ArrayList<Evento> eventosFuturosCondicionados() {
        try {
            if (Simulacion.getInstance().sumNs() >= Simulacion.getInstance().getVariableControl()) {
                double tiempoResolucion = TR.calculate();
                return new ArrayList<Evento>() {{
                    double tiempoActual = Simulacion.getInstance().getTiempoActual();
                    add(new Salida(tiempoActual + tiempoResolucion));
                }};
            }
        } catch (Exception e) {
        }
        return new ArrayList<Evento>();
    }
    
}
