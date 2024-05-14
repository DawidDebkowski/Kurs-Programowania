import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Klasa służąca do obsługi poruszania aktywną figurą 
 */
public class ActiveMoveHandler implements EventHandler<MouseEvent> {
    private CanvasPane canvasPane;
    private MShape activeShape;
    private double startX;
    private double startY;

    public ActiveMoveHandler(CanvasPane cp) {
        canvasPane = cp;
    }

    public void Move(MouseEvent event) {
        double dx = event.getSceneX() - startX;
        double dy = event.getSceneY() - startY;

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
            activeShape.setStroke(CanvasPane.activeColor);
            startX = event.getSceneX();
            startY = event.getSceneY();
            if (event.getButton() == MouseButton.SECONDARY) {
                canvasPane.popupMenu.show(canvasPane, event.getScreenX(), event.getScreenY());
            }
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            Move(event);
        }
    }
}
