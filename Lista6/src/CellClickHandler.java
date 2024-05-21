import java.awt.List;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CellClickHandler implements EventHandler<MouseEvent> {
    private GridCell cell;
    private IActiveListener listener;

    public CellClickHandler(GridCell cell, IActiveListener listener) {
        this.cell = cell;
        this.listener = listener;
    }

    @Override
    public void handle(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            cell.setActive(!cell.isActive());
            listener.onActiveChanged(cell.isActive());

            if (cell.isActive())
                synchronized (listener) {
                    listener.notify();
                }
        } else {
            if (e.getButton() == MouseButton.SECONDARY) {
                funLocking(Color.rgb(255, 0, 0));
            } else {
                funLocking(Color.rgb(0, 255, 0));
            }
        }
    }

    private void funLocking(Color c) {
        cell.setActive(true);
        cell.setBackgroundColor(c);
        listener.onActiveChanged(false);
    }

}
