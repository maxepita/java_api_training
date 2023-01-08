package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = null;
        try {
            int port = Integer.parseInt(args[0]); //Integer.parseInt(args[0]);
            server = new Server(port);
            /*if (args.length > 1) {
                Client client = new Client(server, args[1]);
                client.connect();
            }*/
        } catch (IOException error) {
            throw error;
        }
    }
}
