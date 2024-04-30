import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

enum PossibleShapes {
    Triangle, Rectangle, Circle,
}

/**
 * MovableShape
 */
interface MovableShape {
    public void handleCreationResize(double mouseX, double mouseY);
}
/**
 * ActivableShape
 */
interface ActivableShape {
    public void activate();
    public void setFill(Paint paint);
    public void setStroke(Paint paint);
    public Boolean isHit(double x, double y);
    public void moveTo(double x, double y);
}

public class CanvasPane extends Pane {
    public static Color activeColor = Color.LEMONCHIFFON;

    private PossibleShapes chosenShape;
    private MovableShape selectedShape;
    private ActivableShape activeShape;

    public CanvasPane() {
        chosenShape = PossibleShapes.Circle;
        activeShape = null;

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedShape = null;
                if(activeShape == null)
                {
                    createShape(event.getX(), event.getY());
                }
                else
                {   
                    if(!activeShape.isHit(event.getX(), event.getY()))
                    {
                        activeShape.setStroke(Color.BLACK);
                        setActiveShape(null);
                    }
                }
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(selectedShape != null && activeShape == null)
                {
                    selectedShape.handleCreationResize(event.getX(), event.getY());
                }
            }

        });
    }

    public void setActiveShape(ActivableShape shape)
    {
        activeShape = shape;
    }

    public void setShape(PossibleShapes newShape) {
        chosenShape = newShape;
    }

    private void createShape(double x, double y) {
        Shape shape = null;
        switch (chosenShape) {
            case PossibleShapes.Triangle:
                MTriangle triangle = new MTriangle(x, y, Color.FORESTGREEN);
                shape = triangle;
                break;
            case PossibleShapes.Rectangle:
                MRectanglet rect = new MRectanglet(x, y, Color.CYAN);
                shape = rect;
                break;
            case PossibleShapes.Circle:
                MCircle circle = new MCircle(x, y, Color.BLUEVIOLET);
                shape = circle;
                break;
            default:
                break;
        }
        if (shape != null) {
            selectedShape = (MovableShape)shape;
            shape.setOnMouseClicked(new ActiveMoveHandler(this));
            shape.setOnMouseDragged(new ActiveMoveHandler(this));
            this.getChildren().addAll(shape);
        }
    }
}
