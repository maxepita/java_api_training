package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttpSimple {
    private final HttpClient client;
    private final int port;

    public ClientHttpSimple(int port) {
        this.client = HttpClient.newHttpClient();
        this.port = port;

    }

    public ClientHttpSimple(HttpClient client, int port) {
        this.client = client;
        this.port = port;
    }

    public void SendPost(String adversaryUrl) throws IOException, InterruptedException {
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"I will crush you\"}"))
            .build();
        try{
            this.client.sendAsync(requestPost, BodyHandlers.ofString()).thenAccept(resp -> System.out.println("Response: " +
                resp.statusCode() + " : " + resp.body()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
