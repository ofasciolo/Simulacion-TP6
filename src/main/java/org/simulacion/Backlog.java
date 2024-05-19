package org.simulacion;

import org.simulacion.Eventos.Evento;
import org.simulacion.Eventos.Llegada;
import org.simulacion.Models.Simulacion;

import java.util.ArrayList;

public class Backlog {

    public static double instanteFinal = 60*24; //60*24*365*2;
    public static Evento eventoActual;
    private static Simulacion simulacion = Simulacion.getInstance();
    public static ArrayList<Evento> eventosFuturos = Simulacion.eventosFuturos;

    private static int idEvento = 0;
    
    public static void main (String []args ){
        fijarCondicionesIniciales();
        iniciarSimulacion();
        while(!isOver()) {
            eventoActual = getProximoEvento();
            System.out.println("Evento actual: " + eventoActual.toString());
            avanzarTiempoHasta(eventoActual.getInstante());
            eventosFuturos.addAll(eventoActual.eventosFuturosNoCondicionados());
            eventoActual.actualizarVectorDeEstado();
            eventosFuturos.addAll(eventoActual.eventosFuturosCondicionados());
            imprimirInformacionActual();
            idEvento++;
        }
        calcularResultados();
        imprimirResultados();
    }

    public static void iniciarSimulacion(){
        Llegada llegada = new Llegada(0);
        simulacion.eventosFuturos.add(llegada);
    }

    public static void imprimirInformacionActual(){
        // Aca imprimimos durante la corrida
        // T, evento Numero, NS, 
        System.out.println("Tiempo: " + simulacion.getTiempoActual());
        System.out.println("Evento numero: " + idEvento);
        System.out.println("NS Highest: " + simulacion.getNSH());
        System.out.println("NS High: " + simulacion.getNSH());
        System.out.println("NS Medium: " + simulacion.getNSM());
        System.out.println("NS Low: " + simulacion.getNSL());
        System.out.println("NS Total: " + simulacion.sumNs());
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