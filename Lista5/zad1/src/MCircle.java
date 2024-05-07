import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MCircle extends Circle implements MovableShape, ActivableShape {
    private double startX;
    private double startY;

    public MCircle(double startX, double startY, Paint paint) {
        super(0, paint);

        setCenterX(startX);
        setCenterY(startY);

        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void handleCreationResize(double mouseX, double mouseY) {
        double width = mouseX - startX;
        double height = mouseY - startY;
        setCenterX(startX + width / 2);
        setCenterY(startY + height / 2);
        setRadius(Math.min(Math.abs(width), Math.abs(height)) / 2);
    }
    public void addX(double dx)
    {
        setCenterX(getCenterX() + dx);
    }

    public void addY(double dy)
    {
        setCenterY(getCenterY() + dy);
    }

    @Override
    public Boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(sceneToLocal(x, y));
    }

    @Override
    public void addWidth(double d) {
        setRadius(getRadius()+d*0.5);
    }
    
    @Override
    public void addHeight(double d) {
        setRadius(getRadius()+d*0.5);
    }
}

class ActiveMoveHandler implements EventHandler<MouseEvent> {
    CanvasPane canvasPane;
    ActivableShape activeShape;
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
        startX+=dx;
        startY+=dy;
    }

    @Override
    public void handle(MouseEvent event) {
        activeShape = (ActivableShape) event.getTarget();
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            canvasPane.setActiveShape(activeShape);
            activeShape.setStroke(CanvasPane.activeColor);
            startX = event.getSceneX();
            startY = event.getSceneY();
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            Move(event);
        }
    }

}
