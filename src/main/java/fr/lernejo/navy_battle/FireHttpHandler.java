package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class FireHttpHandler implements HttpHandler {

    final Server srv;

    public FireHttpHandler(Server srv) {
        this.srv = srv;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if (method.equals("GET")) {
            JSONObject response = new JSONObject("{\"consequence\": \"sunk\",  \"shipLeft\":true}");
            sendResponse(exchange, response.toString(),200);
        } else {
            exchange.sendResponseHeaders(400, "BAD REQUEST".length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write("BAD REQUEST".getBytes());
            }
        }
    }

    public void sendResponse(HttpExchange exchange, String response,int code) throws IOException {
        exchange.getResponseHeaders().add("Accept", "application/json");
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(code, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }


}
