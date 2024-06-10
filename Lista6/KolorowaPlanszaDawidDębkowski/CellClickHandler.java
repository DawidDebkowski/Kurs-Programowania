import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Klasa odpowiadająca za obsługę kliknięcia myszą
 */
public class CellClickHandler implements EventHandler<MouseEvent> {
    private GridCell cell;
    private IActiveListener listener;

    /**
     * Podstawowy konstruktor
     * 
     * @param cell     kafelek
     * @param listener odpowiadający kafelkowi wątek
     */
    public CellClickHandler(GridCell cell, IActiveListener listener) {
        this.cell = cell;
        this.listener = listener;
    }

    /**
     * Zmienia aktywność kafelka przy kliknięciu LPM
     */
    @Override
    public void handle(MouseEvent e) {
        // zwykłe blokowanie komórki
        // tak jak w specyfikacji zadania
        if (e.getButton() == MouseButton.PRIMARY) {
            cell.setActive(!cell.isActive());
            listener.setActive(cell.isActive());

            if (cell.isActive())
                synchronized (listener) {
                    listener.notify();
                }
        } 
    }
}
