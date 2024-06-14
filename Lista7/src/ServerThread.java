import java.io.*;
import java.net.*;

interface doStuff {
    public String goDoIt(String arg);
}

public class ServerThread extends Thread {
    private final String ERROR_MESSAGE = "Error: ";
    private BinaryTree<String> stringTree;
    private BinaryTree<Integer> integerTree;
    private BinaryTree<Double> doubleTree;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private TreeType treeType;

    public ServerThread(Socket socket, BinaryTree<String> st, BinaryTree<Integer> it, BinaryTree<Double> dt) {
        this.socket = socket;
        stringTree = st;
        integerTree = it;
        doubleTree = dt;
    }

    public void run() {
        try {
            setupStreams();

            chooseTreeType();
            String line;
            out.println("Podaj komendę: ");
            do {
                System.out.println("Start Loop");

                line = in.readLine();

                String outString = null;
                String[] query = line.split(" ");
                String command = query[0];

                class test<T extends Comparable<T>> {
                    private BinaryTree<T> bt;

                    test(BinaryTree<T> bt, T arg) {
                        this.bt = bt;
                    }

                    public String doStuff(String methodName, T arg) {
                        switch (methodName) {
                            case "serach":
                                String outString = "";
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
                                break;
                        }

                        return "error";
                    }
                }

                doStuff method = null;
                try {
                    String argument = query[1];
                    if (command.equals("search")) {
                        // method = (a) -> {stringTree.search(a);};
                        method.goDoIt(argument);
                        outString = search(argument);
                    } else if (command.equals("insert")) {

                    }
                } catch (NumberFormatException ex) {
                    outString = ERROR_MESSAGE + "zły typ danych";
                } catch (IndexOutOfBoundsException ex) {
                    outString = ERROR_MESSAGE + "za mało argumentów";
                }

                out.println(outString);

            } while (!line.equals("bye"));

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String search(String argument) {
        String outString;
        boolean successful = false;
        if (treeType == TreeType.integer) {
            successful = integerTree.search(Integer.parseInt(argument));
        } else if (treeType == TreeType.doubleT) {
            successful = doubleTree.search(Double.parseDouble(argument));
        } else if (treeType == TreeType.string) {
            successful = stringTree.search(argument);
        }

        // stworzenie odpowiedzi
        outString = "Search complete: ";
        if (!successful) {
            outString += "not ";
        }
        outString += "found";

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
                System.out.println(line);
                System.out.println("key: " + tt.key);
                if (tt.key.equals(line)) {
                    this.treeType = tt;
                    isValid = true;
                }
            }
        }
    }
}