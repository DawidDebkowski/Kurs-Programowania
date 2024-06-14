import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TreeViewer extends Application {
    private Client client;
    private Label consoleOutput;
    private TextField inputField;

    @Override
    public void init() throws Exception{
        client = new Client();
        client.connect("localhost", 4444);
        client.getResponse(); //welcome message
        client.sendCommand("s");
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane content = new BorderPane();
        content.setPrefSize(400, 400);

        HBox buttonBox = new HBox();
        buttonBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        buttonBox.setPadding(new Insets(20));
        buttonBox.setSpacing(10);
        CommandButton insertButton = new CommandButton("Insert", client, inputField, consoleOutput);
        CommandButton searchButton = new CommandButton("Search", client, inputField, consoleOutput);
        CommandButton deleteButton = new CommandButton("Delete", client, inputField, consoleOutput);
        CommandButton drawButton = new CommandButton("Draw", client, inputField, consoleOutput);
        insertButton.setHandler("insert");
        searchButton.setHandler("search");
        deleteButton.setHandler("delete");
        drawButton.setHandler("draw");
        
        inputField = new TextField();

        searchButton.setPrefWidth(Integer.MAX_VALUE);
        insertButton.setPrefWidth(Integer.MAX_VALUE);
        deleteButton.setPrefWidth(Integer.MAX_VALUE);
        drawButton.setPrefWidth(Integer.MAX_VALUE);
        inputField.setPrefWidth(Integer.MAX_VALUE);

        buttonBox.getChildren().addAll(inputField, searchButton,
                insertButton, deleteButton, drawButton);

        content.setTop(buttonBox);

        consoleOutput = new Label();
        content.setBottom(consoleOutput);

        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setTitle("Drzewo Binarne");
        stage.show();
    }

    private void bottomPrint(String text) {
        consoleOutput.setText(text);
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
        this.client = client;
        this.input = inputField;
        this.output = output;
    }

    public void setHandler(String command) {
        this.setOnAction((arg) -> {
            String out = client.sendCommand("search " + input.getText());
            output.setText(out);
        });
    }
}
