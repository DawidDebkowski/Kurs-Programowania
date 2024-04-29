import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class MTriangle extends Polygon implements MovableShape {
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
}
