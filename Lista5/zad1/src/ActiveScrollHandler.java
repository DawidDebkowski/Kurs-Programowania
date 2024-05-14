import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

/*
 * Klasa służaca do obsługi scrollowania - zmniejszania i zwięszkania aktywnej figury.
 */
public class ActiveScrollHandler implements EventHandler<ScrollEvent> {
    private CanvasPane canvasPane;
    private MShape activeShape;

    /**
     * Podstawowy konstruktor.
     * @param cp canvas pane który ma obsługiwać
     */
    public ActiveScrollHandler(CanvasPane cp) {
        canvasPane = cp;
    }

    /**
     * Skaluje figurę jeżeli na niej scrollujemy
     * @param e event scrolla
     */
    private void doScale(ScrollEvent e) {
        double x = e.getSceneX();
        double y = e.getSceneY();

        // Jesli scrollujemy na figurze
        if (activeShape.isHit(x, y)) {
            activeShape.addWidth(e.getDeltaY() * 0.2);
            activeShape.addHeight(e.getDeltaY() * 0.2);
        }
    }

    @Override
    public void handle(ScrollEvent event) {
        activeShape = (MShape) event.getTarget();
        if (canvasPane.getActiveShape() == activeShape) {
            doScale(event);
        }
    }
}
