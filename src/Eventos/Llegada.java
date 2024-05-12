package Eventos;
import java.util.ArrayList;

public class Llegada extends Evento {

    public Llegada(double instante) {
        super(instante);
    }

    @Override
    public void determinarEvento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'determinarEvento'");
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
