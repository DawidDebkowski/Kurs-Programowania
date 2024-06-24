import javafx.application.Application;

/**
 * Uruchamia aplikację klienta GUI.
 * 
 * Trzeba skompilować najpierw Server, później App.
 */
public class App {
    public static void main(String[] args) throws Exception {
        Application.launch(GUIClient.class, args);   
    }
}
