import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CommandButton extends Button{
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
