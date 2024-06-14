public class App {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connect("localhost", 4444);

        TreeViewer view = new TreeViewer();
        view.setClient(client);
        view.show(args);
    }
}
