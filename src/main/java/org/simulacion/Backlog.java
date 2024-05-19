package org.simulacion;

import org.simulacion.Eventos.Evento;
import org.simulacion.Eventos.Llegada;
import org.simulacion.Models.Simulacion;
import org.simulacion.Models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Backlog {

    public static double instanteFinal = 60;
    public static Evento eventoActual;
    private static Simulacion simulacion = Simulacion.getInstance();
    public static ArrayList<Evento> eventosFuturos = Simulacion.eventosFuturos;
    
    public static void main (String []args ){
        fijarCondicionesIniciales();
        iniciarSimulacion();
        while(!isOver()) {
            eventoActual = getProximoEvento();
            avanzarTiempoHasta(eventoActual.getInstante());
            eventoActual.determinarEvento();
            eventosFuturos.addAll(eventoActual.eventosFuturosNoCondicionados());
            actualizarVariableEstado();
            eventosFuturos.addAll(eventoActual.eventosFuturosCondicionados());
            imprimirInformacionActual();
        }
        calcularResultados();
        imprimirResultados();
    }

    public static void iniciarSimulacion(){
        Llegada llegada = new Llegada(0);
        simulacion.eventosFuturos.add(llegada);
    }

    public static void imprimirInformacionActual(){

    }

    public static void actualizarVariableEstado(){
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

    public static void fijarCondicionesIniciales(){
        simulacion.setVariableControl(10);
        simulacion.setTiempoDeActualizacion(4320.0); //3 dias
        simulacion.setValorEstimacion(480); //8 horas (no habiles necesariamente)
    }

    public static Evento getProximoEvento(){
        Evento evento = eventosFuturos.stream().reduce((anterior,actual)->elMenor(anterior,actual)).get();
        eventosFuturos.remove(evento);
        return evento;
    }

    public static void avanzarTiempoHasta(double instante){
        simulacion.setTiempoActual(instante);
    }

    public static Evento elMenor(Evento anterior, Evento actual){
        return anterior.getInstante() >= actual.getInstante() ? actual:anterior;
    }

    public static boolean isOver(){
        return simulacion.getTiempoActual() >= instanteFinal;
    }

    public static void calcularResultados(){

    }

    public static void imprimirResultados(){
        // Creo que aca se calcula el promedio de desfasaje
        System.out.println("Tiempo promedio de permanencia en el sistema de los tickets (PPS): " + simulacion.getSPS());
        System.out.println("Promedio de tickets resueltos por semana (PTS): " + simulacion.getSTRS());
        System.out.println("Promedio de desfase de ticket (PDT): " + simulacion.getSTD());
    }
}