package Eventos;

import java.util.ArrayList;

import Models.Desarrollador;

public class Salida extends Evento {

    public Salida(double instante) {
        super(instante);
    }

    @Override
    public void determinarEvento() {
        Desarrollador desarrollador = simulacion.getProximoDesarrolladorLibre();
        desarrollador.tomarTicket(simulacion.getNextTicket(), instante);
    }

    @Override
    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        return new ArrayList<Evento>();
    }

    @Override
    public ArrayList<Evento> eventosFuturosCondicionados() {
        if(simulacion.sumNs() >= simulacion.getN()) { 
            double TA = FDPS.TA.calculate();
            return new ArrayList<Evento>() {{
                add(new Salida(instante + TA));
            }};
        }
        return new ArrayList<Evento>();
    }
    
}
