import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MCircle extends Circle implements MovableShape, ActivableShape{
    private double startX;
    private double startY;
    
    public MCircle(double startX, double startY, Paint paint)
    {
        super(0, paint);

        // setOnMouseDragged(new ActiveMoveHandler());

        setCenterX(startX);
        setCenterY(startY);

        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void handleCreationResize(double mouseX, double mouseY) {
        double width = mouseX-startX;
        double height = mouseY-startY;
        setCenterX(startX+width/2);
        setCenterY(startY+height/2);
        setRadius(Math.min(Math.abs(width), Math.abs(height))/2);
    }

    public void moveTo(double x, double y)
    {
        setCenterX(x);
        setCenterY(y);
    }

    @Override
    public void activate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    @Override
    public Boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x,y);
    }
}

class ActiveMoveHandler implements EventHandler<MouseEvent> {
    CanvasPane canvasPane;
    ActivableShape activeShape;
    
    public ActiveMoveHandler(CanvasPane cp)
    {
        canvasPane = cp;
    }

    @Override
    public void handle(MouseEvent event) {
        activeShape = (ActivableShape)event.getTarget();
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            canvasPane.setActiveShape(activeShape);
            activeShape.setStroke(CanvasPane.activeColor);
        }
        else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED)
            activeShape.moveTo(event.getX(), event.getY());
    }

}
