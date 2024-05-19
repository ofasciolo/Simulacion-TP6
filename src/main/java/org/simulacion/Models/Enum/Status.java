package org.simulacion.Models.Enum;

public enum Status {
    OPEN, IN_PROGRESS, CLOSED;

    public static Status getStatus(String status) {
        switch (status.toUpperCase()){
            case "SHORT TERM BACKLOG":
            case "LONG TERM BACKLOG":
            case "GATHERING INTEREST":
                return IN_PROGRESS;
            case "CLOSED":
                return CLOSED;
            default:
                return OPEN;
        }
    }


}
