
package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
public class
Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = null;
        try {
            int port = Integer.parseInt(args[0]); //Integer.parseInt(args[0]);
            server = new Server(port);
            if (args.length > 1) {
                ClientHttpHandler client = new ClientHttpHandler();
            }
        } catch (IOException error) {
            throw error;
        }
    }
}
