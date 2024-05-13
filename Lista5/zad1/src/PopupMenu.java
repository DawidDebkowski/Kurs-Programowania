import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class PopupMenu extends ContextMenu {
    public PopupMenu(CanvasPane canvasPane) {
        setupAngles(canvasPane);
        setupColorPicker(canvasPane);
        setStyle("-fx-padding: 3px;");

    }

    private void setupColorPicker(CanvasPane canvasPane) {
        ColorPicker picker = new ColorPicker();
        MenuItem pickColor = new MenuItem(null, picker);
        Menu changeColor = new Menu("Zmień wypełnienie");

        picker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                canvasPane.getActiveShape().setFill(picker.getValue());
            }
        });
        changeColor.getItems().add(pickColor);
        this.getItems().addAll(changeColor);
    }

    private void setupAngles(CanvasPane canvasPane) {
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
        MenuItem angle90 = new MenuItem("Obróć o 90° w prawo");
        angle90.setOnAction(new RotationHandler(90));
        MenuItem angle270 = new MenuItem("Obróć o 90° w lewo");
        angle270.setOnAction(new RotationHandler(270));
        MenuItem angle15 = new MenuItem("Obróć o 15° w prawo");
        angle15.setOnAction(new RotationHandler(15));
        MenuItem angle345 = new MenuItem("Obróć o 15° w lewo");
        angle345.setOnAction(new RotationHandler(345));

        this.getItems().addAll(flip, angle90, angle270, angle15, angle345);
    }

}
