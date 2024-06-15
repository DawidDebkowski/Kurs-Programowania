import java.io.*;
import java.net.*;

enum TreeCommand {
    search("search"), insert("insert"),
    delete("delete"), draw("draw"), changeTree("another_tree");

    public final String name;

    private TreeCommand(String name) {
        this.name = name;
    }
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
                System.out.println(line);
                String[] query = line.split(" ");
                String command = query[0];

                if (command.equals(TreeCommand.changeTree.name)) {
                    System.out.println("Wybieranie rodzaju drzewa");
                    chooseTreeType();
                    out.println("Podaj komendę: " + "(" + METHODS + ")");
                    continue;
                }

                String outString = null;
                try {
                    // wykonaj odpowiednią metodę dla wybranego typu
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
                        String[] args = new String[query.length-1];
                        System.arraycopy(query, 1, args, 0, query.length-1);
                        outString = hi.runMethod(command, args);
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

    public String runMethod(String methodName, T[] args) {
        TreeCommand command = null;
        for (TreeCommand tCommand : TreeCommand.values()) {
            if (methodName.equals(tCommand.name)) {
                command = tCommand;
                break;
            }
        }
        if (command == null) {
            return "No such method";
        }

        String outString = "";
        switch (command) {
            case TreeCommand.search:
                boolean successful = bt.search(args[0]);

                outString = "Search complete: ";
                if (!successful) {
                    outString += "not ";
                }
                outString += "found";

                return outString;
            case TreeCommand.insert:
                bt.insert(args[0]);
                return bt.draw();
            case TreeCommand.delete:
                bt.delete(args[0]);
                return bt.draw();
            case TreeCommand.draw:
                return bt.draw();
            default:
                return "Not implemented.";
        }
    }
}