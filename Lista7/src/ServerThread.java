import java.io.*;
import java.net.*;

/**
 * Możliwe komendy wraz z odpowiadającymi im napisami.
 */
enum TreeCommand {
    search("search"), insert("insert"), delete("delete"), draw("draw"), changeTree("another_tree"),
    help("help"), bye("bye");

    public final String name;

    private TreeCommand(String name) {
        this.name = name;
    }

    public static String getAllMethods() {
        String methods = "";
        for (TreeCommand tCommand : TreeCommand.values()) {
            methods += tCommand.name + " ";
        }
        return methods;
    }
}

/**
 * Wątek obsługujący konkretnego klienta. Komunikacja odbywa się za pomocą
 * socketa i strumienia napisów.
 * 1 konkretna komenda zawsze zwraca 1 odpowiedź.
 * Możliwe komendy znajdują się w TreeCommand
 * 
 * @see TreeCommand
 */
public class ServerThread extends Thread {
    private final String ERROR_MESSAGE = "Blad: ";
    private final String METHODS;
    private BinaryTree<String> treeString;
    private BinaryTree<Integer> treeInteger;
    private BinaryTree<Double> treeDouble;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private TreeType treeType;

    public ServerThread(Socket socket, BinaryTree<String> st, BinaryTree<Integer> it, BinaryTree<Double> dt) {
        this.socket = socket;
        treeString = st;
        treeInteger = it;
        treeDouble = dt;

        // wypisz wszystkie metody
        METHODS = TreeCommand.getAllMethods();
    }

    public void run() {
        try {
            setupStreams();

            // pierwsze wybranie drzewa
            System.out.println("Wybieranie rodzaju drzewa");
            chooseTreeType();

            // rozpoczęcie głównej pętli
            String line = "";
            out.println("Podaj komendę: " + "(" + METHODS + ")");
            while (!line.equals(TreeCommand.bye.name)) {
                System.out.println("Start pętli komendy");

                line = in.readLine();
                System.out.println(line);
                String[] query = line.split(" ");
                String command = query[0];

                // możliwość zmiany drzewa
                if (command.equals(TreeCommand.changeTree.name)) {
                    System.out.println("Wybieranie rodzaju drzewa");
                    chooseTreeType();
                    out.println("Podaj komendę: " + "(" + METHODS + ")");
                    continue; // powrót do głównej pętli
                }

                // wykonanie metody dla wybranego typu drzewa
                String outString = executeCommand(query, command);

                // zwrócenie rezultatu
                out.println(outString);
            }
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Dla wybranego typu drzewa przetwarza argumenty na wymagany typ i wykonuje
     * metodę
     * 
     * Bierze argumenty z zapytania na miejscach [1, długość]
     * 
     * @param query   zapytanie
     * @param command komenda do wykonania
     * @return rezultat
     */
    private String executeCommand(String[] query, String command) {
        String outString = null;
        try {
            if (treeType == TreeType.integer) {
                TreeMethodHandler<Integer> hi = new TreeMethodHandler<Integer>(treeInteger);
                Integer[] args = new Integer[query.length - 1];
                for (int i = 1; i < query.length; i++) {
                    args[i - 1] = Integer.parseInt(query[i]);
                }
                outString = hi.runMethod(command, args);
            } else if (treeType == TreeType.doubleT) {
                TreeMethodHandler<Double> hi = new TreeMethodHandler<Double>(treeDouble);
                Double[] args = new Double[query.length - 1];
                for (int i = 1; i < query.length; i++) {
                    args[i - 1] = Double.parseDouble(query[i]);
                }
                outString = hi.runMethod(command, args);
            } else if (treeType == TreeType.string) {
                TreeMethodHandler<String> hi = new TreeMethodHandler<String>(treeString);
                String[] args = new String[query.length - 1];
                System.arraycopy(query, 1, args, 0, query.length - 1);
                outString = hi.runMethod(command, args);
            }
        } catch (IndexOutOfBoundsException ex) {
            outString = ERROR_MESSAGE + "za mało argumentów";
        } catch (NumberFormatException ex) {
            outString = ERROR_MESSAGE + "zły typ danych";
        }
        return outString;
    }

    private void setupStreams() throws IOException {
        // Odbieranie od socketa
        InputStream input = socket.getInputStream();
        in = new BufferedReader(new InputStreamReader(input));

        // Wysyłanie do socketa
        OutputStream output = socket.getOutputStream();
        out = new PrintWriter(output, true);
    }

    private void chooseTreeType() throws IOException, NullPointerException {
        boolean isValid = false;
        while (!isValid) {
            out.println("Wybierz typ drzewa. s - string, i - integer, d - double");
            String line = in.readLine();
            for (TreeType tt : TreeType.values()) {
                if (tt.key.equals(line)) {
                    this.treeType = tt;
                    isValid = true;
                }
            }
        }
    }
}