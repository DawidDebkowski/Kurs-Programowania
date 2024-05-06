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
    public void setStroke(Paint paint);
    public Boolean isHit(double x, double y);
    public void addX(double dx);
    public void addY(double dy);
    public void addWidth(double d);
	public void addHeight(double d);
}

public class CanvasPane extends Pane {
    public static Color activeColor = Color.LEMONCHIFFON;

    private PossibleShapes chosenShape;
    private MovableShape selectedShape;
    private ActivableShape activeShape;

    public CanvasPane() {
        this.setStyle("-fx-background-color: black;");
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
                        deactiveShape();
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

    public ActivableShape getActiveShape()
    {
        return activeShape;
    }

    public void setActiveShape(ActivableShape shape)
    {
        deactiveShape();
        activeShape = shape;
    }

    private void deactiveShape()
    {
        if(activeShape!=null)
        {
            activeShape.setStroke(Color.BLACK);
            activeShape = null;
        }
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
                MRectangle rect = new MRectangle(x, y, Color.CYAN);
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
            shape.setOnMousePressed(new ActiveMoveHandler(this));
            shape.setOnMouseDragged(new ActiveMoveHandler(this));
            shape.setOnScroll(new ActivableScrollHandler(this));
            this.getChildren().addAll(shape);
        }
    }
}
