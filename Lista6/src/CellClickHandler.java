import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
        } else {
            // własne blokowanie
            // komórka nie zmienia swojego koloru ale sąsiednie komórki nadal korzystają z jej koloru
            // można tak tworzyć ciekawe obrazki
            if (e.getButton() == MouseButton.SECONDARY) {
                specialLock(Color.rgb(255, 0, 0));
            } else {
                specialLock(Color.rgb(0, 255, 0));
            }
        }
    }

    private void specialLock(Color c) {
        cell.setActive(true);
        cell.setBackgroundColor(c);
        listener.setActive(false);
    }
}
