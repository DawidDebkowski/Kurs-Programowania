import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.event.*;

public class MTriangle extends Polygon implements MovableShape {
    private double startX;
    private double startY;

    public MTriangle(double startX, double startY, Paint paint)
    {
        super();
        
        setOnMousePressed(new MTriangleMovementHandler());
        setOnMouseDragOver(new MTriangleMovementHandler());
        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                setStroke(paint);
            }
            
        });
        this.setFill(paint);
        this.startX = startX;
        this.startY = startY;
    }


    @Override
    public void handleCreationResize(double mouseX, double mouseY) {
        double width = mouseX-startX;
        double height = mouseY-startY;
        
        getPoints().removeAll(getPoints());
        getPoints().addAll(new Double[]{ 
            width/2+startX, startY, 
            startX, height+startY, 
            width+startX, height+startY,   
         }); 
    }
}

class MTriangleMovementHandler implements EventHandler<MouseEvent>
{
    private MTriangle triangle;
    private double x;
    private double y;

    private void doMove(double targetX, double targetY)
    {
        double dx = targetX - x;
        double dy = targetY - y;
    
          // Jesli nacisnelismy na elipse
        // if (triangle.isHit(x, y)) {
        {
            triangle.setLayoutX(dx);
            triangle.setLayoutY(dy);
        }
        x += dx;
        y += dy;    
    }
    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            x = event.getX();
            y = event.getY();
            triangle.setStroke(CanvasPane.activeColor);
        }
        else if(event.getEventType() == MouseEvent.DRAG_DETECTED)
        {
            doMove(event.getX(), event.getY());
        }
    }
}
