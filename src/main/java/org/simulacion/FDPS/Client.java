package org.simulacion.FDPS;
import java.net.http.HttpClient;

public class Client {
    private static HttpClient httpClient = HttpClient.newBuilder().build();

    public static HttpClient getHttpClient() {
        return httpClient;
    }
}
