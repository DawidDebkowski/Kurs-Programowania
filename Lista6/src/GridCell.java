import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GridCell extends Pane {
    public GridCell(Color color) {
        this.setMinSize(50, 50);
        this.setBackground(new Background(
                new BackgroundFill(color, null, new Insets(5))));
    }

    public void setBackgroundColor(Color color) {
        this.setBackground(new Background(
                new BackgroundFill(color, null, new Insets(5))));
    }
}
