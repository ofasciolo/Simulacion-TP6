package Eventos;

import java.util.ArrayList;

import Models.Desarrollador;
import Models.Simulacion;

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
        if(Simulacion.getInstance().sumNs() >= Simulacion.getInstance().getVariableControl()) {
            double TA = FDPS.TA.calculate();
            return new ArrayList<Evento>() {{
                add(new Salida(getInstante() + TA));
            }};
        }
        return new ArrayList<Evento>();
    }
    
}
