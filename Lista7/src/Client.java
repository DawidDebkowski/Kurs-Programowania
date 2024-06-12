import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client<T extends Comparable<T>> {
    private Server<T> server;

    public Client(Server<T> server) {
        this.server = server;
    }

    public Client() {
        this.server = new Server<T>();
    }

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
                text = console.readLine("Enter text: ");

                // Wysyłanie do serwera
                out.println(text);
                // Odbieranie z serwera
                System.out.println(in.readLine());

            } while (!text.equals("bye"));
            socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public void mainLoop() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("-----Klient DRZEWA BINARNEGO-----");
        System.out.println("""
                Wybierz polecenie:
                s - search
                i - insert
                d - delete
                w - draw
                """);
        String method = userInput.nextLine();
        switch (method) {
            case "s":
                String input = userInput.nextLine();
                T processed = null;
                try {
                    if (processed instanceof Integer) {
                        int a = Integer.parseInt(input);
                    }
                } catch (Exception e) {

                }
                break;

            default:
                break;
        }
        Server<?> server;

    }
}
