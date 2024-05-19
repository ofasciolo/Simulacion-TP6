package FDPS;

import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IA {
    public static double calculate() {
        return Math.random() * 15 + 1; // Random number between 1 and 15
    }

    public static void main(String[] args) {
        try {
            // Create URL object
            URL url = new URL("http://localhost:5000/IA");

            // Create HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            // Check if request was successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the prop value in the response
                System.out.println("Prop value: " + response.toString());
            } else {
                System.out.println("Request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}