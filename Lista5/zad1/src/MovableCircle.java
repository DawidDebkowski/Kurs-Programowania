import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MovableCircle extends Circle implements MovableShape{
    private double startX;
    private double startY;
    
    public MovableCircle(double startX, double startY, Paint paint)
    {
        super(0, paint);

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
}
