import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PopupMenu extends ContextMenu {
    public PopupMenu(CanvasPane canvasPane) {
        class RotationHandler implements EventHandler<ActionEvent> {
            private double degrees;

            RotationHandler(double degrees) {
                this.degrees = degrees;
            }

            @Override
            public void handle(ActionEvent arg0) {
                canvasPane.getActiveShape().rotate(degrees);
            }

        }
        MenuItem flip = new MenuItem("Odwróć");
        flip.setOnAction(new RotationHandler(180));
        MenuItem angle90 = new MenuItem("Obróc o 90° w prawo");
        angle90.setOnAction(new RotationHandler(90));
        MenuItem angle270 = new MenuItem("Obróc o 90° w lewo");
        angle270.setOnAction(new RotationHandler(270));
        MenuItem angleAny = new MenuItem("Dowolnie obróc");
        angleAny.setOnAction(new RotationHandler(45));

        this.getItems().addAll(flip, angle90, angle270, angleAny);
        setStyle("-fx-padding: 3px;");
    }
}
