package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    final private int port;
    final private HttpHandler get_ping = exchange -> {
        String body = "OK";
        exchange.sendResponseHeaders(200, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    };



    Server(int port) throws IOException {
        this.port = port;
        Executor executorService = Executors.newFixedThreadPool(1);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ping", new PingHttpHandler());
        server.createContext("/api/game/start", new PostHttpHandler(port, ""));
        server.setExecutor(executorService);
        server.start();
    }
}
