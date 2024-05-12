package FDPS;
import java.util.Random;

public class IA {
    public static double calculate() {
        Random random = new Random();
        return random.nextDouble() * 15 + 1; // Numero aleatorio entre 1 y 15, para tener algo
    }
}