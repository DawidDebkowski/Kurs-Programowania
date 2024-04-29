
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MovableRect extends Rectangle implements MovableShape {
    private double startX;

    public double getStartX() {
        return startX;
    }

    private double startY;

    public double getStartY() {
        return startY;
    }

    public MovableRect(double startX, double startY, Paint paint) {
        super(0, 0, paint);
        setX(startX);
        setY(startY);

        this.startX = startX;
        this.startY = startY;
        // setOnMouseDragged(new RectMoveHandler());
    }
}

class RectMoveHandler implements EventHandler<MouseEvent> {
    MovableRect rect;

    @Override
    public void handle(MouseEvent event) {
        rect = (MovableRect) event.getTarget();
        // if(event.getEventType() == MouseEvent.MOUSE_DRAGGED)
        rect.setWidth(rect.getX() + event.getX());
    }
}