import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Aplikacja javafx odpowiadająca za GUI do komunikacji Klienta z Serwerem
 * 
 * Tworzy proste okno, w którym za pomocą przycisków i pola tekstowego można
 * wysyłać komendy do serwera i wyświetlać odpowiedź lub całe drzewo binarne w
 * graficznej formie.
 * Na dole ekranu posiada wyświetlacz oryginalnej odpowiedzi serwera. Można
 * także zmienić typ drzewa za pomocą menu.
 */
public class GUIClient extends Application {
    private TreeIOBox IOBox;
    private Client client;
    private boolean connected = false;
    private final String HOST_NAME = "localhost";
    private final int PORT = 4444;

    // chciałem początkowo dać połączenie z klientem i serwerem w metodzie init() javafx 
    // ale wtedy nie mogłbym wyświetlać okna dialogowego w przypadku błędu
    @Override
    /**
     * Metoda tworzy całą szatę wizualną aplikacji, a później łączy się z klientem.
     * 
     * Tworzy obiekt klienta, za pomocą którego będzie się komunikować z serwerem.
     * Wybiera też od razu typ drzewa String.
     * 
     */
    public void start(Stage stage) throws Exception {
        MenuBar mainMenu = setupTreeChangeMenu();

        IOBox = new TreeIOBox(this);
        IOBox.setPrefSize(500, 500);
        IOBox.init();

        BorderPane menuHolder = new BorderPane();
        menuHolder.setTop(mainMenu);
        menuHolder.setCenter(IOBox);

        Scene scene = new Scene(menuHolder);
        stage.setScene(scene);
        stage.setTitle("Klient drzewa binarnego");
        stage.show();

        client = new Client();
        defaultConnection();

        if (!connected)
            return;
        IOBox.refresh();
    }

    /**
     * Tworzy MenuBar odpowiadający za zmianę drzewa
     * 
     * @return główny MenuBar aplikacji
     */
    private MenuBar setupTreeChangeMenu() {
        final String infoText = "Wybrane drzewo: ";

        MenuBar mainMenu = new MenuBar();
        Menu changeTree = new Menu("Zmień drzewo");
        Menu treeInfo = new Menu(infoText + "String"); // "String" ze wzgledu na od razu wybrane drzewo String
        ArrayList<MenuItem> trees = new ArrayList<MenuItem>();
        for (TreeType treeType : TreeType.values()) {
            MenuItem item = new MenuItem("Drzewo " + treeType.name);
            item.setOnAction((arg) -> {
                ChangeTreeType(treeType);
                treeInfo.setText(infoText + treeType.name);
            });
            trees.add(item);
        }
        for (MenuItem menuItem : trees) {
            changeTree.getItems().add(menuItem);
        }
        mainMenu.getMenus().addAll(changeTree, treeInfo);
        return mainMenu;
    }

    /**
     * Wysyła komendę zmiany drzewa i klucz drzewa.
     * Potem wyświetla drzewo poprzez przycisk draw.
     * 
     * @param type nowy typ drzewa
     */
    private void ChangeTreeType(TreeType type) {
        sendCommand(TreeCommand.changeTree.name);
        sendCommand(type.key);
        IOBox.refresh();
    }

    /**
     * Bezpieczne na błędy połączenia z serwerem wysłanie komendy przez klienta i
     * odebranie odpowiedzi serwera.
     * 
     * @param command
     * @return
     */
    public String sendCommand(String command) {
        try {
            return client.sendCommand(command);
        } catch (NullPointerException | IOException e) {
            showReconnectAlert();
        }
        return "Krytyczny błąd połączenia";
    }

    /**
     * Pokazuje okno dialogowe z błędem połączenia z serwerem.
     * Można spróbować się połączyć ponownie lub zamknąć program.
     */
    private void showReconnectAlert() {
        Alert alert = new Alert(AlertType.ERROR,
                "Połączenie z serwerem nieudane. Czy spróbować połączyć się ponownie?", ButtonType.OK,
                ButtonType.CLOSE);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            defaultConnection();
        } else {
            Platform.exit();
        }
        return;
    }

    /**
     * Bezpieczne na błedy połączenie z klientem i ustawienie typu drzewa na string.
     */
    private void defaultConnection() {
        try {
            connected = client.connect(HOST_NAME, PORT);
            client.getResponse(); // wiadomosc powitania (prośba o wybranie drzewa)
            sendCommand("s");
        } catch (NullPointerException | IOException e) {
            showReconnectAlert();
        }
    }

    @Override
    public void stop() {
        try {
            if (!connected)
                return;
            sendCommand("bye");
            client.disconnect();
        } catch (IOException | NullPointerException ex) {
            System.err.println("Nie udało się rozłączyć");
            ex.printStackTrace();
        }
    }
}
