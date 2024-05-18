package Repository;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

import Eventos.Evento;
import Models.Desarrollador;
import Models.Ticket;
import Models.Enum.Priority;

public class State {
    private static State instance;

    // Variables para el manejo de eventos
    public static ArrayList<Evento> eventosFuturos;
    protected double TPLL; // Tiempo entre llegadas
    protected double[] TPS; // Tiempo de servicio
    private double tiempo;

    // Variables de control
    private int N = 10; // Numero de desarrolladores
    private double TiempoDeActualizacion = 20.0; // Valor en minutos
    
    // Variables de estado
    private Queue<Ticket> NSHT; // La cola de mas alta prioridad
    private Queue<Ticket> NSH; // La cola de alta prioridad
    private Queue<Ticket> NSL; // La cola de baja prioridad
    private Queue<Ticket> NSM; // La cola de media prioridad
    private ArrayList<Desarrollador> desarrolladores;

    // Variables para usar durante la corrida
    private int i;
    private int j;
    private int k;
    private int NT; // Numero total de tickets
    private double SPS; // Sumatoria de permanencia en el sistema
    private int STRS; // Sumatoria de tickets resueltos por semana
    private int STD; // Sumatoria de desfase de tickets
    
    private State() {
        eventosFuturos = new ArrayList<>();
        // Cargo las colas
        NSHT = new LinkedList<Ticket>();
        NSH = new LinkedList<Ticket>();
        NSL = new LinkedList<Ticket>();
        NSM = new LinkedList<Ticket>();
        // Creo los desarrolladores en base a N (Cantidad de desarrolladores)
        desarrolladores = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            desarrolladores.add(new Desarrollador());
        }

    }
    
    public static State getInstance() {
        if (instance == null) {
            instance = new State();
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
    
    public int getI() {
        return i;
    }
    
    public void setI(int i) {
        this.i = i;
    }
    
    public int getJ() {
        return j;
    }
    
    public void setJ(int j) {
        this.j = j;
    }
    
    public int getK() {
        return k;
    }
    
    public void setK(int k) {
        this.k = k;
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

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public int getN() {
        return N;
    }

    public double getTiempoDeActualizacion() {
        return TiempoDeActualizacion;
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

    public Queue<Ticket> getByPriority(Priority priority) {
        switch (priority) {
            case HIGH:
                return NSH;
            case MEDIUM:
                return NSM;
            case LOW:
                return NSL;
            default:
                return null;
        }
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
