package org.simulacion.Eventos;

import java.util.ArrayList;

import org.simulacion.FDPS.TR;
import org.simulacion.Models.Desarrollador;
import org.simulacion.Models.Simulacion;

public class Salida extends Evento {

    public Salida(double instante) {
        super(instante);
    }

    @Override
    public void determinarEvento() {
        Desarrollador desarrollador = Simulacion.getInstance().getProximoDesarrolladorLibre();
        desarrollador.tomarTicket(Simulacion.getInstance().getNextTicket(), getInstante());
    }

    @Override
    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        return new ArrayList<Evento>();
    }

    @Override
    public ArrayList<Evento> eventosFuturosCondicionados() {
        try{
            if(Simulacion.getInstance().sumNs() >= Simulacion.getInstance().getVariableControl()) {
                double tiempoResolucion = TR.calculate();
                return new ArrayList<Evento>() {{
                    add(new Salida(getInstante() + tiempoResolucion));
                }};
            }
        }catch(Exception e){}
        return new ArrayList<Evento>();
    }
    
}
