package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GameStartHttpHandler implements HttpHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    public boolean isNull(HttpExchange exchange ) {
        JsonNode json;
        if(!Objects.isNull(exchange))
            return true;
        else
            return false;
    }
    public List<String> readJsonFile(HttpExchange exchange) throws IOException {
        JsonNode obj;
        if(isNull(exchange))
            obj = this.mapper.readTree(new File("src/main/java/fr/lernejo/files/schema.json"));
        else
            obj = this.mapper.readTree(exchange.getRequestBody());
        Iterator<String> iterat = obj.fieldNames();
        List<String> keys = new ArrayList<>();
        iterat.forEachRemaining(e -> keys.add(e));
        return keys;
    }

    public void makeRequest (HttpExchange exchange) throws IOException {
        String validJson = "{\"id\":\"1\", \"url\":\"http://localhost:" + exchange.getLocalAddress().getPort() + "\", \"message\":\"hello\"}";
        List<String> fileContent = readJsonFile(null);
        List<String> HttpBodyContent = readJsonFile(exchange);
        if (!(HttpBodyContent.equals(fileContent))) {
            exchange.sendResponseHeaders(400, "BAD REQUEST".length());
            try (OutputStream op = exchange.getResponseBody()) {
                op.write("NOT FOUND".getBytes());
            }
        }
        else {
            exchange.sendResponseHeaders(202, validJson.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(validJson.getBytes());
            }
        }
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            makeRequest(exchange);
            exchange.close();
        }
        else {
            exchange.sendResponseHeaders(404, "NOT FOUND".length());
            try (OutputStream op = exchange.getResponseBody()) {
                op.write("NOT FOUND".getBytes());
            }
        }
        exchange.close();
    }
}
