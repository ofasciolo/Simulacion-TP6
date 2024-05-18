import Eventos.Evento;
import Repository.State;

import java.util.ArrayList;

public class Backlog {

    public static double instanteFinal;
    public static Evento eventoActual;
    private static State simulacion = State.getInstance();
    public static ArrayList<Evento> eventosFuturos = State.eventosFuturos;
    
    public static void main (String []args ){
        fijarCondicionesIniciales();
        while(!isOver()) {
            eventoActual = getProximoEvento();
            avanzarTiempoHasta(eventoActual.instante);
            eventoActual.determinarEvento();
            eventosFuturos.addAll(eventoActual.eventosFuturosNoCondicionados());
            // eventoActual.actualizarVariableEstado();
            eventosFuturos.addAll(eventoActual.eventosFuturosCondicionados());
            imprimirInformacionActual();
        }
        calcularResultados();
        imprimirResultados();
    }

    public static void imprimirInformacionActual(){
        
    }

    public static void actualizarVariableEstado(){

    }

    public static void fijarCondicionesIniciales(){
        
    }

    public static Evento getProximoEvento(){
        Evento evento = eventosFuturos.stream().reduce((anterior,actual)->elMenor(anterior,actual)).get();
        eventosFuturos.remove(evento);
        return evento;
    }

    public static void avanzarTiempoHasta(double instante){
        simulacion.setTiempo(instante);
    }

    public static Evento elMenor(Evento anterior, Evento actual){
        return anterior.getInstante() >= actual.getInstante() ? actual:anterior;
    }

    public static boolean isOver(){
        return simulacion.getTiempo() >= instanteFinal;
    }

    public static void calcularResultados(){
    }

    public static void imprimirResultados(){
        // Creo que aca se calcula el promedio de desfasaje
    }
}