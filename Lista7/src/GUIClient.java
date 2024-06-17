import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    private Client client;
    private CommandButton drawButton;

    @Override
    /**
     * Metoda uruchamiana przez javafx przed startem.
     * Tworzy obiekt klienta, za pomocą którego będzie się komunikować z serwerem.
     * 
     * Wybiera też od razu typ drzewa String.
     */
    public void init() throws Exception {
        client = new Client();
        client.connect("localhost", 4444);
        client.getResponse(); // welcome message
        client.sendCommand("s");
    }

    @Override
    public void start(Stage stage) throws Exception {
        MenuBar mainMenu = setupTreeChangeMenu();

        TreeIOBox content = new TreeIOBox(client);
        content.setPrefSize(500, 500);
        content.init();

        BorderPane menuHolder = new BorderPane();
        menuHolder.setTop(mainMenu);
        menuHolder.setCenter(content);

        Scene scene = new Scene(menuHolder);
        stage.setScene(scene);
        stage.setTitle("Klient drzewa binarnego");
        stage.show();
    }

    private MenuBar setupTreeChangeMenu() {
        MenuBar mainMenu = new MenuBar();
        Menu changeTree = new Menu("Change Tree");
        ArrayList<MenuItem> trees = new ArrayList<MenuItem>();
        for (TreeType treeType : TreeType.values()) {
            MenuItem item = new MenuItem("Drzewo " + treeType.name);
            item.setOnAction((arg) -> {
                ChangeTreeType(treeType);
            });
            trees.add(item);
        }
        for (MenuItem menuItem : trees) {
            changeTree.getItems().add(menuItem);
        }
        mainMenu.getMenus().add(changeTree);
        return mainMenu;
    }

    /**
     * Wysyła komendę zmiany drzewa i klucz drzewa.
     * Potem wyświetla drzewo poprzez przycisk draw.
     * 
     * @param type nowy typ drzewa
     */
    private void ChangeTreeType(TreeType type) {
        client.sendCommand(TreeCommand.changeTree.name);
        client.sendCommand(type.key);
        drawButton.fire(); // wyswietl nowe drzewo
    }

    public void show(String... args) {
        launch(args);
    }
}
