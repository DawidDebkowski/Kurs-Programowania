import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Klasa służąca do obsługi poruszania i zaznaczenia aktywnej figury. Włącza
 * także menu pod prawym przyciskiem.
 */
public class ActiveMoveHandler implements EventHandler<MouseEvent> {
    private CanvasPane canvasPane;
    private MShape activeShape;
    private double startX;
    private double startY;

    public ActiveMoveHandler(CanvasPane cp) {
        canvasPane = cp;
    }

    /**
     * Porusza figurę na wskazaną pozycję.
     * 
     * @param x docelowa pozycja X
     * @param y docelowa pozycja Y
     */
    public void Move(double x, double y) {
        double dx = x - startX;
        double dy = y - startY;

        if (activeShape.isHit(startX, startY)) {
            activeShape.addX(dx);
            activeShape.addY(dy);
        }
        startX += dx;
        startY += dy;
    }

    @Override
    public void handle(MouseEvent event) {
        activeShape = (MShape) event.getTarget();
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            canvasPane.setActiveShape(activeShape);
            startX = event.getSceneX();
            startY = event.getSceneY();

            // PopupMenu włączanie/wyłączanie
            if (event.getButton() == MouseButton.SECONDARY) {
                canvasPane.popupMenu.show(canvasPane, event.getScreenX(), event.getScreenY());
            } else if (event.getButton() == MouseButton.PRIMARY) {
                canvasPane.popupMenu.hide();
            }
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            // getSceneX/Y, a nie getX/Y, ponieważ potrzebuje X/Y w odniesieniu do
            // CanvasPane, a nie obiektu
            // Aby trójkąt można było poprawnie poruszać
            Move(event.getSceneX(), event.getSceneY());
        }
    }
}
