import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GridCell extends Pane {
    public String name;

    public GridCell(Color color, String name) {
        this.name = name;
        this.setMinSize(50, 50);
        this.setBackground(new Background(
                new BackgroundFill(color, null, new Insets(5))));
    }

    public synchronized void setBackgroundColor(Color color) {
        Platform.runLater(() -> this.setBackground(new Background(
                new BackgroundFill(color, null, null))));
    }

    public Color getBackgroundColor() {
        return (Color)this.getBackground().getFills().getFirst().getFill();
    }
}
