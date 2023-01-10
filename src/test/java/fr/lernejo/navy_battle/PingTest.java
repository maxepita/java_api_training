package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class PingTest {
    @Test
    void ping() {
        Server server = null;
        try {
            server = new Server(9696);
        } catch (Exception e) {
            Assertions.assertEquals(0, 1, "Number of exception on server");
        }
        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9696/ping"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(200, response.statusCode(), "Status code 200 was expected for a POST on /ping");
            Assertions.assertEquals("OK", response.body(), "OK body was expected for a POST  on /ping");
        } catch (InterruptedException | IOException | URISyntaxException e) {
            Assertions.assertEquals(0, 1, "Number of exception for POST on /ping");
        }
    }
}
