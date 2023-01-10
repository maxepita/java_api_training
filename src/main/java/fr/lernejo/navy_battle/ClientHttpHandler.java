package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;

public class ClientHttpHandler {
    public static final void postRequest(int port, String adversaryUrl) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json").setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello the winner\"}")).build();

        HttpResponse<String> res = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(res.body());
    }
}
