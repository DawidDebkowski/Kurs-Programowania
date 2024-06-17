import javafx.scene.control.Button;

public class CommandButton extends Button{
    public CommandButton(String text) {
        super(text);
        // this.setStyle(getStyle() + "-fx-background-color: #CDE4FF;");
        this.setPrefWidth(Integer.MAX_VALUE);
    }
}
