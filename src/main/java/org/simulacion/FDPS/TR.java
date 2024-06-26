package org.simulacion.FDPS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TR {
    public static double calculate() throws IOException, InterruptedException {
        HttpClient httpClient = Client.getHttpClient();
        URI url = URI.create("http://127.0.0.1:5000/IA");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();

        Response tiempoResolucion = objectMapper.readValue(response.body(), new TypeReference<Response>(){});

        // TODO: Fix this func
        return Math.abs(tiempoResolucion.getValue());
    }
}
