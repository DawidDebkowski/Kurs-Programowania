import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;

/**
 * Klasa odpowiadająca za menu kontekstowe pod prawym przyciskiem myszy
 */
public class PopupMenu extends ContextMenu {

    /**
     * Inicjuje menu kontekstowe do późniejszego pokazania
     * 
     * @param canvasPane docelowy canvasPane
     */
    public PopupMenu(CanvasPane canvasPane) {
        setupAngles(canvasPane);
        setupColorPicker(canvasPane);
        setStyle("-fx-padding: 3px;");
    }

    private void setupColorPicker(CanvasPane canvasPane) {
        // Chciałem podpiąć ColorPicker pod MenuItem
        // jednak wtedy "Custom Color" crashuje całą aplikację i z tego co rozumiem nie da się tego naprawić
        // Dlatego powstał ten Dialog
        MenuItem changeColor = new MenuItem("Zmień wypełnienie");

        Dialog<VBox> dialog = new Dialog<VBox>();
        dialog.setTitle("Wybierz kolor");

        ColorPicker picker = new ColorPicker();
        Button confirm = new Button("Zmień");
        VBox box = new VBox(picker, confirm);
        dialog.getDialogPane().setGraphic(box);

        ButtonType cancel = new ButtonType("Zamknij", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(cancel);
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                canvasPane.getActiveShape().setFill(picker.getValue());
            }
        });

        changeColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                dialog.showAndWait();
            }
        });
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
