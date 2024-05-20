import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridCell extends Rectangle {
    public String name;

    public GridCell(Color color, String name) {
        super(50, 50, color);
        this.name = name;
        // this.setMinSize(50, 50);
        // setFill(color);
        // this.setBackground(new Background(
        //         new BackgroundFill(color, null, new Insets(5))));
    }

    public synchronized void setBackgroundColor(Color color) {
        // this.setBackground(getBackground().fill(color));
        Platform.runLater(()->this.setFill(color));
        // this.setStyle("-fx-background-color: " + color.toString() + ";");
        // System.out.println("-fx-background-color: " + color.toString() + ";");
        // this.setBackground(new Background(
        //         new BackgroundFill(color, null, new Insets(5))));
    }
}
