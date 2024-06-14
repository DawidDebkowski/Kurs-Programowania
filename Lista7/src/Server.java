import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

enum TreeType {
    string("s"), integer("i"), doubleT("d");

    final String key;

    TreeType(String key) {
        this.key = key;
    }
}

public class Server {
    private static BinaryTree<String> stringTree = new BinaryTree<String>();
    private static BinaryTree<Integer> integerTree = new BinaryTree<Integer>();
    private static BinaryTree<Double> doubleTree = new BinaryTree<Double>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            System.out.println("Server is listening on port 4444");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(socket, stringTree, integerTree, doubleTree).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
