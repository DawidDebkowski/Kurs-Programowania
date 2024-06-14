import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client<T extends Comparable<T>> {
    public Client() {}

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 4444);
            // Wysyłanie do serwera
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Console console = System.console();
            String text;

            do {
                // Odbieranie z serwera
                System.out.println(in.readLine());

                text = console.readLine("Enter text: ");

                // Wysyłanie do serwera
                out.println(text);

            } while (!text.equals("bye"));
            socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
