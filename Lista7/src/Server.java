import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Możliwe typy drzewa wraz z ich nazwą oraz odpowiadającą literą do wybrania
 * podczas komunikacji z serwerem
 */
enum TreeType {
    string("s", "String"), integer("i", "Integer"), doubleT("d", "Double");

    final String key;
    final String name;

    TreeType(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
/**
 * Klasa odpowiadająca za podłączanie nowych klientów i przechowywanie drzew.
 * Każdy klient ma pracuje na tych samych drzewach.
 */
public class Server {
    private static BinaryTree<String> stringTree = new BinaryTree<String>();
    private static BinaryTree<Integer> integerTree = new BinaryTree<Integer>();
    private static BinaryTree<Double> doubleTree = new BinaryTree<Double>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            System.out.println("Słucham na 4444");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Podłączono nowego klienta");

                new ServerThread(socket, stringTree, integerTree, doubleTree).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
