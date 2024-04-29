
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MRectanglet extends Rectangle implements MovableShape {
    private double startX;
    private double startY;

    public MRectanglet(double startX, double startY, Paint paint) {
        super(0, 0, paint);
        setX(startX);
        setY(startY);

        this.startX = startX;
        this.startY = startY;
    }

    public void handleCreationResize(double mouseX, double mouseY)
    {
        double width = mouseX-startX;
        double height = mouseY-startY;
        if(width > 0)
        {
            setWidth(width); 
        }
        else 
        {
            setX(mouseX);
            setWidth(-width); 
        }
        if(height > 0)
        {
            setHeight(height); 
        }
        else
        {
            setY(mouseY);
            setHeight(-height); 
        }
    }
}

class RectMoveHandler implements EventHandler<MouseEvent> {
    MRectanglet rect;

    @Override
    public void handle(MouseEvent event) {
        rect = (MRectanglet) event.getTarget();
        // if(event.getEventType() == MouseEvent.MOUSE_DRAGGED)
        rect.setWidth(rect.getX() + event.getX());
    }
}