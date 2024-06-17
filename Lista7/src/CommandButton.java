import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class CommandButton extends Button{
    public CommandButton(String text) {
        super(text);
        // this.setStyle(getStyle() + "-fx-background-color: #CDE4FF;");
        this.setPrefWidth(Integer.MAX_VALUE);
    }
}
