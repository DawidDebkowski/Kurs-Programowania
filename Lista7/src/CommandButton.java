import javafx.scene.control.Button;

public class CommandButton extends Button{
    public CommandButton(String text) {
        super(text);
        this.setPrefWidth(Integer.MAX_VALUE);
    }
}
