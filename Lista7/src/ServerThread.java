import java.io.*;
import java.net.*;
 

public class ServerThread extends Thread {
    private Socket socket;
    private Server server;
    private BufferedReader in;
    private PrintWriter out;
    private TreeType treeType;
 
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
 
    public void run() {
        try {
             setupStreams();
    
            String line;
            do {
                System.out.println("new");
                // Wypisywanie na serwerze
                String type = null;
                while (type == null) {
                    try {
                        type = ChooseTreeType();
                        System.out.println(type);
                    } catch (NullPointerException e) {
                        type = null;
                    }
                }
                line = type;

                do {
                    line = in.readLine();

                    out.println("->("+line+")");
                } while (!line.equals("bye"));
                // Wysyłanie do socketa
                // out.println("-> ("+type+")");
    
            } while (!line.equals("bye"));
    
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void setupStreams() throws IOException {
        //Odbieranie od socketa
        InputStream input = socket.getInputStream();
        in = new BufferedReader(new InputStreamReader(input));
   
        //Wysyłanie do socketa
        OutputStream output = socket.getOutputStream();
        out = new PrintWriter(output, true);
    }

    private String ChooseTreeType() throws IOException, NullPointerException {
        out.println("Wybierz typ drzewa. s - string, i - integer, d - double");
        String line = in.readLine();
        tree = Server.getTree(line);
        return line;
    }
}