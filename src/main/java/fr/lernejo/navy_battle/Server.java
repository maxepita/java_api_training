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

    final int server_port;
    final HttpHandler get_ping = exchange -> {
        String body = "OK";
        exchange.sendResponseHeaders(200, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    };

    Server(int server_port) throws IOException {
            // Définition du port à utiliser (ici, le port passé en paramètre)
            this.server_port = server_port;

            // Création d'un ExecutorService avec un seul thread
            Executor executorService = Executors.newFixedThreadPool(1);

            // Création du serveur HTTP qui écoute sur le port spécifié
            HttpServer server = HttpServer.create(new InetSocketAddress(server_port), 0);

            // Association du chemin /ping à une implémentation de HttpHandler
            server.createContext("/ping", new PingHttpHandler());

            // Association du chemin /api/game/start à une implémentation de HttpHandler pour le verbe POST
            // server.createContext("/api/game/start", new GameStartHttpHandler());

            // Démarrage du serveur
            server.setExecutor(executorService);
            server.start();
        }
    }

