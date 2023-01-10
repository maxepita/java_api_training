package fr.lernejo.navy_battle;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttpHandler {
    private final HttpClient client;
    private final int port;
    public final String url;
    public ClientHttpHandler(int port, String url) {
        this.client = HttpClient.newHttpClient();
        this.port = port;
        this.url = url;

    }

    public ClientHttpHandler(HttpClient client, int port, String url) {
        this.client = client;
        this.port = port;
        this.url = url;
    }

    public void SendStart() throws IOException, InterruptedException {
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(url + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"May the best code win\"}")).build();

        try{
            this.client.sendAsync(requestPost, HttpResponse.BodyHandlers.ofString()).thenAccept(r -> System.out.println("Reply: " +
                r.statusCode() + " : " + r.body()));        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
