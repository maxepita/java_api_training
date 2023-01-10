package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class PingTest {
    @Test
    public void testPingRequestOnServerShouldReturn200() throws IOException, InterruptedException {
        Server server = new Server(9876);
        HttpResponse<String> resp = null;
        HttpRequest requestGet = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9876/ping"))
            .setHeader("Accept", "application/json")
            .build();
        resp =   HttpClient.newHttpClient().send(requestGet, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(200, resp.statusCode());
        HttpClient.newHttpClient().send(requestGet, HttpResponse.BodyHandlers.ofString());
    }
}
