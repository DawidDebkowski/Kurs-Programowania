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
        System.out.println(x + " " + y);
        System.out.println(getLayoutX());
        System.out.println(getBoundsInParent());
        System.out.println(getBoundsInLocal());
        return getBoundsInLocal().contains(sceneToLocal(x, y)); //Inne nie działają, nie wiem czemu
    }

    @Override
    public void addX(double dx) {
        setLayoutX(getLayoutX() + dx);
    }
    
    @Override
    public void addY(double dy) {
        setLayoutY(getLayoutY() + dy);
    }

    @Override
    public void addWidth(double d) {
        setScaleX(getScaleX()+d*0.01);
    }
    
    @Override
    public void addHeight(double d) {
        setScaleY(getScaleY()+d*0.01);
    }
}
