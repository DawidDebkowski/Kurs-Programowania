import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;

enum PossibleShapes {
    Triangle, Rectangle, Circle,
}

/**
 * MovableShape
 */
interface MovableShape {
    public void setWidth(double w);
    public void setHeight(double h);

    public void setX(double x);
    public void setY(double y);

    public double getStartX();
    public double getStartY();
}

public class CanvasPane extends Pane {
    private PossibleShapes chosenShape;
    private MovableShape selectedShape;

    public CanvasPane() {
        chosenShape = PossibleShapes.Circle;
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createShape(event.getX(), event.getY());
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(selectedShape != null)
                {
                    double width = event.getX()-selectedShape.getStartX();
                    double height = event.getY()-selectedShape.getStartY();
                    if(width > 0)
                    {
                        selectedShape.setWidth(width); 
                        // selectedShape.setX(selectedShape.getStartX());
                    }
                    else 
                    {
                        selectedShape.setX(event.getX());
                        selectedShape.setWidth(-width); 
                    }
                    if(height > 0)
                    {
                        selectedShape.setHeight(height); 
                        // selectedShape.setY(selectedShape.getStartY());
                    }
                    else
                    {
                        selectedShape.setY(event.getY());
                        selectedShape.setHeight(-height); 
                    }
                }
            }

        });
    }

    public void setShape(PossibleShapes newShape) {
        chosenShape = newShape;
    }

    private void createShape(double x, double y) {
        Shape shape = null;
        switch (chosenShape) {
            case PossibleShapes.Triangle:
                System.err.println("trojkat");
                break;
            case PossibleShapes.Rectangle:
                MovableRect rect = new MovableRect(x, y, Color.CYAN);
                // rect.setX(x);
                // rect.setY(y);
                shape = rect;
                break;
            case PossibleShapes.Circle:
                MovableCircle circle = new MovableCircle(x, y, Color.BLUEVIOLET);
                shape = circle;
                break;
            default:
                break;
        }
        if (shape != null) {
            selectedShape = (MovableShape)shape;
            this.getChildren().addAll(shape);
        }
    }
}
