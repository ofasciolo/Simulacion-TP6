package Models;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

import Eventos.Evento;
import Models.Enum.Priority;

public class Simulacion {
    private static Simulacion instance;

    // Variables para el manejo de eventos
    public static ArrayList<Evento> eventosFuturos;
    protected double TPLL; // Tiempo entre llegadas
    protected double[] TPS; // Tiempo de servicio
    private double tiempoActual;

    // Variables de control
    private int N; // Numero de desarrolladores
    private double TiempoDeActualizacion; // Valor en minutos
    private int valorEstimacion; //Valor en minutos
    
    // Variables de estado
    private Queue<Ticket> NSHT; // La cola de mas alta prioridad
    private Queue<Ticket> NSH; // La cola de alta prioridad
    private Queue<Ticket> NSL; // La cola de baja prioridad
    private Queue<Ticket> NSM; // La cola de media prioridad
    private ArrayList<Desarrollador> desarrolladores;

    // Variables para usar durante la corrida
    private int NT; // Numero total de tickets
    private double SPS; // Sumatoria de permanencia en el sistema
    private int STRS; // Sumatoria de tickets resueltos por semana
    private int STD; // Sumatoria de desfase de tickets
    
    private Simulacion() {
        eventosFuturos = new ArrayList<>();
        // Cargo las colas
        NSHT = new LinkedList<Ticket>();
        NSH = new LinkedList<Ticket>();
        NSL = new LinkedList<Ticket>();
        NSM = new LinkedList<Ticket>();
        // Creo los desarrolladores en base a N (Cantidad de desarrolladores)
        desarrolladores = new ArrayList<>();
    }
    
    public static Simulacion getInstance() {
        if (instance == null) {
            instance = new Simulacion();
        }
        return instance;
    }
    
    // Getters and Setters
    public Queue<Ticket> getNSHT() {
        return NSHT;
    }
    
    public void setNSHT(Queue<Ticket> NSHT) {
        this.NSHT = NSHT;
    }
    
    public Queue<Ticket> getNSH() {
        return NSH;
    }
    
    public void setNSH(Queue<Ticket> NSH) {
        this.NSH = NSH;
    }
    
    public Queue<Ticket> getNSL() {
        return NSL;
    }
    
    public void setNSL(Queue<Ticket> NSL) {
        this.NSL = NSL;
    }
    
    public Queue<Ticket> getNSM() {
        return NSM;
    }
    
    public void setNSM(Queue<Ticket> NSM) {
        this.NSM = NSM;
    }
    
    public int getNT() {
        return NT;
    }
    
    public void setNT(int NT) {
        this.NT = NT;
    }
    
    public double getSPS() {
        return SPS;
    }
    
    public void setSPS(double SPS) {
        this.SPS = SPS;
    }
    
    public int getSTRS() {
        return STRS;
    }
    
    public void setSTRS(int STRS) {
        this.STRS = STRS;
    }
    
    public int getSTD() {
        return STD;
    }
    
    public void setSTD(int STD) {
        this.STD = STD;
    }

    public double getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(double tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public int getVariableControl() {
        return N;
    }
    public void setVariableControl(int N) {
        this.N = N;
        desarrolladores = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            desarrolladores.add(new Desarrollador());
        }
    }

    public double getTiempoDeActualizacion() {
        return TiempoDeActualizacion;
    }

    public void setTiempoDeActualizacion(double tiempoDeActualizacion) {
        TiempoDeActualizacion = tiempoDeActualizacion;
    }

    public int getValorEstimacion() {
        return valorEstimacion;
    }

    public void setValorEstimacion(int valorEstimacion) {
        this.valorEstimacion = valorEstimacion;
    }

    // More complex methods
    public Ticket getNextTicket() {
        Ticket nextTicket = null;
        
        if (!NSHT.isEmpty()) {
            nextTicket = NSHT.poll();
        } else if (!NSH.isEmpty()) {
            nextTicket = NSH.poll();
        } else if (!NSM.isEmpty()) {
            nextTicket = NSM.poll();
        } else if (!NSL.isEmpty()) {
            nextTicket = NSL.poll();
        }
        
        return nextTicket;
    }

    public int sumNs() {
        return NSHT.size() + NSH.size() + NSM.size() + NSL.size();
    }

    public int puestoLibre() {
        int minIndex = 0;
        double minTPS = TPS[0];
        
        for (int i = 1; i < TPS.length; i++) {
            if (TPS[i] < minTPS) {
                minIndex = i;
                minTPS = TPS[i];
            }
        }
        
        return minIndex;
    }

    public void actualizarTPS(int puesto, double tiempo) {
        TPS[puesto] = tiempo;
    }

    public Desarrollador getProximoDesarrolladorLibre() {
        Desarrollador proximoDesarrolladorLibre = null;
        double minTPS = desarrolladores.get(0).TPS;
        
        for (Desarrollador desarrollador : desarrolladores) {
            if (desarrollador.TPS < minTPS) {
                minTPS = desarrollador.TPS;
                proximoDesarrolladorLibre = desarrollador;
            }
        }
        
        return proximoDesarrolladorLibre;
    }
}
