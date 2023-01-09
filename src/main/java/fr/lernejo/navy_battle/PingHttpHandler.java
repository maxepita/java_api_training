package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.UncheckedIOException;

public class PingHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) {
            try {
                httpExchange.sendResponseHeaders(200, "OK".length());
                httpExchange.getResponseBody().write("OK".getBytes());
                httpExchange.getResponseBody().close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
}

