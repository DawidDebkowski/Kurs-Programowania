import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner userInput = new Scanner(System.in);
        System.out.println("-----DRZEWO BINARNE-----");
        System.out.println("Wybierz typ drzewa: (S - String, I - Integer, D - Double)");
        String type = userInput.nextLine();
        Client<?> client = null;
        switch (type) {
            case "S":
                client = new Client<String>();
                break;
            case "I":
                client = new Client<Integer>();
                break;
            case "D":
                client = new Client<Double>();
                break;
        }

        if(client == null) {
            System.out.println("błędny typ");
            close(userInput);
        }
        client.mainLoop();
        userInput.close();

        TreeViewer view = new TreeViewer();
        view.show(args);
    }

    private static void close(Scanner s) {
        s.close();
        System.exit(0);
    }
}
