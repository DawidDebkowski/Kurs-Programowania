
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MRectangle extends Rectangle implements MovableShape, ActivableShape {
    private double startX;
    private double startY;

    public MRectangle(double startX, double startY, Paint paint) {
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

    @Override
    public Boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(sceneToLocal(x, y));
    }

    @Override
    public void addX(double dx) {
        setX(getX()+dx);
    }
    
    @Override
    public void addY(double dy) {
        setY(getY()+dy);
    }

    @Override
    public void addWidth(double d) {
        setWidth(getWidth()+d);
    }
    
    @Override
    public void addHeight(double d) {
        setHeight(getHeight()+d);
    }

    @Override
    public void rotate(double degrees) {
        setRotate(getRotate()+degrees);
    }
}

class ActivableScrollHandler implements EventHandler<ScrollEvent>{
    CanvasPane canvasPane;
    ActivableShape activeShape;
    public ActivableScrollHandler(CanvasPane cp) {
        canvasPane = cp;
    }

    private void doScale(ScrollEvent e) {    
        double x = e.getSceneX();
        double y = e.getSceneY();
        // Jesli nacisnelismy na elipse
        if (activeShape.isHit(x, y)) {                 
            activeShape.addWidth(e.getDeltaY()*0.2);
            activeShape.addHeight(e.getDeltaY()*0.2);
        }
    }            

    @Override
    public void handle(ScrollEvent event) {
        activeShape = (ActivableShape) event.getTarget();
        if (canvasPane.getActiveShape() == activeShape)
        {
            doScale(event);
        }
    }
  }