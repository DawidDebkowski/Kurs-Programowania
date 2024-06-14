import java.io.*;
import java.net.*;

interface runMethod {
    public String goDoIt(String arg);
}

public class ServerThread extends Thread {
    private final String ERROR_MESSAGE = "Error: ";
    private final String METHODS = "search, insert, delete, draw";
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
    }

    public void run() {
        try {
            setupStreams();

            System.out.println("Wybieranie rodzaju drzewa");
            chooseTreeType();
            String line = "";
            out.println("Podaj komendę: " + "(" + METHODS + ")");
            while (!line.equals("bye")) {
                System.out.println("Start pętli komendy");
                
                line = in.readLine();
                
                if (line.equals("another_tree")) {
                    System.out.println("Wybieranie rodzaju drzewa");
                    chooseTreeType();
                    out.println("Podaj komendę: " + "(" + METHODS + ")");
                    continue;
                }

                String outString = null;
                try {
                    // wez input klienta
                    String[] query = line.split(" ");
                    String command = query[0];
                    String argument = "";
                    
                    //TODO quick band aid
                    if(!command.equals("draw")) {
                        argument = query[1];
                    }

                    // wykonaj odpowiednią metodę dla wybranego typu
                    if (treeType == TreeType.integer) {
                        TreeMethodHandler<Integer> hi = new TreeMethodHandler<Integer>(treeInteger);
                        outString = hi.runMethod(command, Integer.parseInt(argument));
                    } else if (treeType == TreeType.doubleT) {
                        TreeMethodHandler<Double> hi = new TreeMethodHandler<Double>(treeDouble);
                        outString = hi.runMethod(command, Double.parseDouble(argument));
                    } else if (treeType == TreeType.string) {
                        TreeMethodHandler<String> hi = new TreeMethodHandler<String>(treeString);
                        outString = hi.runMethod(command, argument);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    outString = ERROR_MESSAGE + "za mało argumentów";
                } catch (NumberFormatException ex) {
                    outString = ERROR_MESSAGE + "zły typ danych";
                }

                out.println(outString);
            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
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

class TreeMethodHandler<T extends Comparable<T>> {
    private BinaryTree<T> bt;

    TreeMethodHandler(BinaryTree<T> bt) {
        this.bt = bt;
    }

    public String runMethod(String methodName, T arg) {
        String outString = "";
        switch (methodName) {
            case "search":
                boolean successful = bt.search(arg);

                outString = "Search complete: ";
                if (!successful) {
                    outString += "not ";
                }
                outString += "found";

                return outString;
            case "insert":
                bt.insert(arg);
                return bt.draw();
            case "delete":
                bt.delete(arg);
                return bt.draw();
            case "draw":
                return bt.draw();
            default:
                return "No such method.";
        }
    }
}