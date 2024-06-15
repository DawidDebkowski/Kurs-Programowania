import java.util.ArrayList;

import javafx.application.Application;
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

public class TreeViewer extends Application {
    private Client client;
    private Label consoleOutput;
    private TextField inputField;
    private HBox buttonBox;
    private CommandButton drawButton;

    @Override
    public void init() throws Exception {
        client = new Client();
        client.connect("localhost", 4444);
        client.getResponse(); // welcome message
        ChangeTreeType(TreeType.string); //podstawowy typ drzewa
    }

    @Override
    public void start(Stage stage) throws Exception {
        MenuBar mainMenu = setupTreeChangeMenu();

        BorderPane content = new BorderPane();
        content.setPrefSize(500, 500);

        consoleOutput = new Label();
        consoleOutput.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        consoleOutput.setPrefWidth(Integer.MAX_VALUE);
        consoleOutput.setAlignment(Pos.BASELINE_CENTER);
        ;

        buttonBox = setupInputBox();
        content.setTop(buttonBox);
        content.setBottom(consoleOutput);

        BorderPane menuHolder = new BorderPane();
        menuHolder.setTop(mainMenu);
        menuHolder.setCenter(content);

        Scene scene = new Scene(menuHolder);
        stage.setScene(scene);
        stage.setTitle("Drzewo Binarne");
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

    private HBox setupInputBox() {
        HBox buttonBox = new HBox();
        buttonBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        buttonBox.setPadding(new Insets(20));
        buttonBox.setSpacing(10);

        inputField = new TextField();
        inputField.setPrefWidth(Integer.MAX_VALUE);

        CommandButton insertButton = new CommandButton("Wstaw", client, inputField, consoleOutput);
        CommandButton searchButton = new CommandButton("Wyszukaj", client, inputField, consoleOutput);
        CommandButton deleteButton = new CommandButton("Usuń", client, inputField, consoleOutput);
        CommandButton drawButton = new CommandButton("Narysuj", client, inputField, consoleOutput);
        insertButton.setHandler(TreeCommand.insert.name);
        searchButton.setHandler(TreeCommand.search.name);
        deleteButton.setHandler(TreeCommand.delete.name);
        drawButton.setHandler(TreeCommand.draw.name);
        this.drawButton = drawButton;

        buttonBox.getChildren().addAll(inputField, searchButton,
                insertButton, deleteButton, drawButton);
        return buttonBox;
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
