import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MovableCircle extends Circle implements MovableShape{
    private double width;
    private double height;
    
    private double startX;

    public double getStartX() {
        return startX;
    }

    private double startY;

    public double getStartY() {
        return startY;
    }

    public MovableCircle(double startX, double startY, Paint paint)
    {
        super(10, paint);

        setCenterX(startX);
        setCenterY(startY);

        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void setWidth(double w) {
        width = w;
        setRadius(w/2);
    }

    @Override
    public void setHeight(double h) {
        height = h;
        setRadius(h/2);
    }

    @Override
    public void setX(double x) {
        setCenterX(startX+(x-startX)/2);
    }

    @Override
    public void setY(double y) {
        setCenterY(startY+(y-startY)/2);
    }
}
