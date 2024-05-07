import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class PopupMenu extends ContextMenu {
    public PopupMenu() {
        MenuItem flip = new MenuItem("flip");

        this.getItems().addAll(flip);
    }
}
