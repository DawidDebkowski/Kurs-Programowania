import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect("localhost", 4444);
        client.consoleMainLoop();
    }

    public void consoleMainLoop() {
        try {
            Console console = System.console();
            String text = "";

            while (!text.equals("bye")) {
                // Odbieranie z serwera
                System.out.println(in.readLine());

                text = console.readLine("Enter text: ");
                // Wysyłanie do serwera
                out.println(text);
            }
            socket.close();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public String sendCommand(String command) {
        String response;
        try {
            out.println(command);
            response = in.readLine();
        } catch (IOException ex) {
            response = "I/O error: " + ex.getMessage();
        }
        return response;
    }

    public void GUIMainLoop() {

    }

    public void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            // Wysyłanie do serwera
            out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
