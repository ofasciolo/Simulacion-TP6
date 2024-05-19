package org.simulacion.Models.Enum;

import org.simulacion.Models.Simulacion;
import org.simulacion.Models.Ticket;

import java.util.Queue;

public enum Priority {

    LOW, MEDIUM, HIGH, HIGHEST;

    public static Priority findPriorityTicket(String priority) {
        switch (priority.toUpperCase()){
            case "MEDIUM": return MEDIUM;
            case "HIGH": return HIGH;
            case "HIGHEST": return HIGHEST;
            default: return LOW;
        }
    }

    public static Queue<Ticket> getQueueByPriority(Priority priority){
        switch (priority) {
            case HIGH:
                return Simulacion.getInstance().getNSH();
            case MEDIUM:
                return Simulacion.getInstance().getNSM();
            case HIGHEST:
                return Simulacion.getInstance().getNSHT();
            default:
                return Simulacion.getInstance().getNSL();
        }
    }
}
