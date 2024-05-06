import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.event.*;

public class MTriangle extends Polygon implements MovableShape, ActivableShape {
    private double startX;
    private double startY;

    public MTriangle(double startX, double startY, Paint paint)
    {
        super();

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

    @Override
    public Boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }

    @Override
    public void addX(double dx) {
        setLayoutX(getLayoutX()+dx);
    }
    
    @Override
    public void addY(double dy) {
        setLayoutY(getLayoutY()+dy);
    }
}
