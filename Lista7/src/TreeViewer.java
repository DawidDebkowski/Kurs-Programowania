import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class TreeViewer extends Application {
    private Client client;
    private Label consoleOutput;
    private TextField inputField;
    private HBox buttonBox;

    @Override
    public void init() throws Exception{
        client = new Client();
        client.connect("localhost", 4444);
        client.getResponse(); //welcome message
        client.sendCommand("s");
    }

    @Override
    public void start(Stage stage) throws Exception {
        MenuBar mainMenu = setupTreeChangeMenu();
        
        BorderPane content = new BorderPane();
        content.setPrefSize(400, 400);
        
        consoleOutput = new Label();
        consoleOutput.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        consoleOutput.setPrefWidth(Integer.MAX_VALUE);
        consoleOutput.setAlignment(Pos.BASELINE_CENTER);;
        
        setupInputBox();
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

    private void setupInputBox() {
        buttonBox = new HBox();
        buttonBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        buttonBox.setPadding(new Insets(20));
        buttonBox.setSpacing(10);

        inputField = new TextField();
        inputField.setPrefWidth(Integer.MAX_VALUE);


        CommandButton insertButton = new CommandButton("Insert", client, inputField, consoleOutput);
        CommandButton searchButton = new CommandButton("Search", client, inputField, consoleOutput);
        CommandButton deleteButton = new CommandButton("Delete", client, inputField, consoleOutput);
        CommandButton drawButton = new CommandButton("Draw", client, inputField, consoleOutput);
        insertButton.setHandler("insert");
        searchButton.setHandler("search");
        deleteButton.setHandler("delete");
        drawButton.setHandler("draw");
        
        buttonBox.getChildren().addAll(inputField, searchButton,
        insertButton, deleteButton, drawButton);
    }

    private void ChangeTreeType(TreeType type) {
        client.sendCommand("another_tree");
        client.sendCommand(type.key);
    }

    public void show(String... args) {
        launch(args);
    }
}

class CommandButton extends Button{
    Client client;
    TextField input;
    Label output;

    public CommandButton(String text, Client client, TextField inputField, Label output) {
        super(text);
        this.setPrefWidth(Integer.MAX_VALUE);
        this.client = client;
        this.input = inputField;
        this.output = output;
    }

    public void setHandler(String command) {
        this.setOnAction((arg) -> {
            String out = client.sendCommand(command + " " + input.getText());
            output.setText(out);
        });
    }
}
