package Repository;

import java.util.ArrayList;
import java.util.Queue;

import Eventos.Evento;
import Models.Ticket;

public class State {
    private static State instance;

    // Variables para el manejo de eventos
    public static ArrayList<Evento> eventosFuturos;
    protected double TPLL; // Tiempo entre llegadas
    protected double[] TPS; // Tiempo de servicio
    private double tiempo;

    // Variables de control
    private int N = 10; // Numero de desarrolladores
    
    // Variables de estado
    private Queue<Ticket> NSHT; // Numero de tickets en la cola de mas alta prioridad
    private Queue<Ticket> NSH; // Numero de tickets en la cola de alta prioridad
    private Queue<Ticket> NSL; // Numero de tickets en la cola de baja prioridad
    private Queue<Ticket> NSM; // Numero de tickets en la cola de media prioridad

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
}
