import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Klasa służąca do łączenia i komunikacji z serwerem. Może funkcjonować jako
 * osobny program konsolowy bez GUI, albo można podłączyć do niej GUI aby
 * usprawnić komunikację.
 * 
 * Potrafi wysyłać komendy tekstowe do połączonego serwera i odczytywać
 * odpowiedzi.
 */
public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Tworzy pustego klienta
     */
    public Client() {
    }

    /**
     * Wywołanie konsolowej wersji klienta.
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.connect("localhost", 4444);
        client.consoleMainLoop();
    }

    /**
     * Pętla i logika rozmowy z serwerem przez konsolę.
     * 1 pytanie 1 odpowiedź.
     * Wyświetla odpowiedzi w konsoli.
     */
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
            disconnect();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    /**
     * Wysyła komendę na serwer i odczytuje odpowiedź
     * 
     * @param command tekst do wysłania
     * @return napis odpowiedzi
     */
    public String sendCommand(String command) {
        out.println(command);
        return getResponse();
    }

    /**
     * Odczytuje pojedynczą odpowiedź serwera
     * 
     * @return napis odpowiedzi
     */
    public String getResponse() {
        String response;
        try {
            response = in.readLine();
        } catch (IOException ex) {
            response = "I/O error: " + ex.getMessage();
        }
        return response;
    }

    /**
     * Łączy klienta z wybranym hostem na danym porcie
     * 
     * @param host adres hosta
     * @param port numer portu
     */
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

    /**
     * Rozłącza się z połączonym hostem
     * 
     * @throws IOException gdy socket.close() się nie powiedzie
     */
    public void disconnect() throws IOException {
        socket.close();
    }
}
