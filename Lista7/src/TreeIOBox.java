import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Guziki i wizualizacja drzewa. 
 * 
 * Tree Input/Output Box. Klasa odpowiada za wygląd oraz logikę guzików
 * wysyłania. Także za wyświetlenie wizualizacji drzewa.
 */
public class TreeIOBox extends BorderPane {
    private GUIClient client;

    // Output
    private Label consoleOutput;
    private TreeVisualizer visualizer;

    // Input
    private TextField inputField;
    private CommandButton insertButton;
    private CommandButton searchButton;
    private CommandButton deleteButton;
    private CommandButton drawButton;

    /**
     * Przypisuje klasie konkretnego klienta.
     * Wymagana inicjacja do poprawnego działania klasy.
     * 
     * @param client klient
     */
    public TreeIOBox(GUIClient client) {
        this.client = client;
    }

    /**
     * Inicjacja wyglądu oraz logiki.
     */
    public void init() {
        this.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
        setupInputBox();
        setupOutputs();
        setupInputLogic();
    }

    /**
     * Odświeża output poprzez sztuczne kliknięcie guzika draw.
     */
    public void refresh() {
        drawButton.fire();
    }

    /**
     * Ustawia wizualną część outputu
     * Tworzy konsolę na dole oraz środek do wizualizacji drzewa
     */
    private void setupOutputs() {
        consoleOutput = new Label();
        consoleOutput.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        consoleOutput.setPrefWidth(Integer.MAX_VALUE);
        consoleOutput.setAlignment(Pos.BASELINE_CENTER);

        visualizer = new TreeVisualizer();

        this.setCenter(visualizer);
        this.setBottom(consoleOutput);
    }

    /**
     * Tworzy wizualną część inputu
     * Panel z guzikami do komend oraz pole tekstowe
     */
    private void setupInputBox() {
        HBox buttonBox = new HBox();
        buttonBox.setBackground(new Background(new BackgroundFill(Color.web("#1D3857"), null, null)));
        buttonBox.setPadding(new Insets(20));
        buttonBox.setSpacing(10);

        inputField = new TextField();
        inputField.setPrefWidth(Integer.MAX_VALUE);

        insertButton = new CommandButton("Wstaw");
        searchButton = new CommandButton("Wyszukaj");
        deleteButton = new CommandButton("Usuń");
        drawButton = new CommandButton("Narysuj");

        buttonBox.getChildren().addAll(inputField, searchButton,
                insertButton, deleteButton, drawButton);

        this.setTop(buttonBox);
    }

    /**
     * Ustawia efekt przy kliknięciu dla każdego przycisku
     */
    private void setupInputLogic() {
        class OnClickHandler implements EventHandler<ActionEvent> {
            String command;

            public OnClickHandler(String command) {
                this.command = command;
            }

            @Override
            public void handle(ActionEvent arg0) {
                String out = client.sendCommand(command + " " + inputField.getText());
                consoleOutput.setText(out);
                if(command != TreeCommand.search.name) {
                    visualizer.visualizeTree(client.sendCommand(TreeCommand.draw.name));
                }
            }
        }

        insertButton.setOnAction(new OnClickHandler(TreeCommand.insert.name));
        searchButton.setOnAction(new OnClickHandler(TreeCommand.search.name));
        deleteButton.setOnAction(new OnClickHandler(TreeCommand.delete.name));
        drawButton.setOnAction(new OnClickHandler(TreeCommand.draw.name));
    }
}
