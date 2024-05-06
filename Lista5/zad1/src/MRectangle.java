
import javafx.event.*;
import javafx.scene.input.MouseEvent;
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
        return getBoundsInLocal().contains(x, y);
    }

    @Override
    public void addX(double dx) {
        setX(getX()+dx);
    }
    
    @Override
    public void addY(double dy) {
        setY(getY()+dy);
    }
}