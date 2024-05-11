package Models.Enum;

public enum Priority {

    LOW, MEDIUM, HIGH, HIGHEST;

    public static Priority findPriority(String priority) {
        switch (priority.toUpperCase()){
            case "MEDIUM": return MEDIUM;
            case "HIGH": return HIGH;
            case "HIGHEST": return HIGHEST;
            default: return LOW;
        }
    }
}
