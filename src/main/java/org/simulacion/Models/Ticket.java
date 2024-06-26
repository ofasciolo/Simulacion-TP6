package org.simulacion.Models;

import org.simulacion.Models.Enum.Priority;
import org.simulacion.Models.Enum.Status;

import java.security.Timestamp;
import java.util.Date;

public class Ticket {
    private String summary;
    private String id;
    private String type;
    private Status status;
    private Priority priority;
    private double createdTime;
    private double inProgressTime;
    private double resolvedTime;
    private String components;
    private double votes;
    private String severity;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public double getVotes() {
        return votes;
    }

    public void setVotes(double votes) {
        this.votes = votes;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public double getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(double createdTime) {
        this.createdTime = createdTime;
    }

    public double getInProgressTime() {
        return inProgressTime;
    }

    public void setInProgressTime(double readyTime) {
        this.inProgressTime = readyTime;
    }

    public double getResolvedTime() {
        return resolvedTime;
    }

    public void setResolvedTime(double resolvedTime) {
        this.resolvedTime = resolvedTime;
    }

    public double getDesfase(){
        double tiempoDeResolucionReal = resolvedTime - inProgressTime;
        double tiempoDeResolucionEstimado = votes*Simulacion.getInstance().getValorEstimacion();
        return tiempoDeResolucionReal - tiempoDeResolucionEstimado;
    }

    public boolean shouldUPPriority(){
        double tiempoEstimado = votes*Simulacion.getInstance().getValorEstimacion();
        double tiempoTranscurridoEnBacklog =  Simulacion.getInstance().getTiempoActual() - createdTime;
        return tiempoTranscurridoEnBacklog >= tiempoEstimado;
    }
}
