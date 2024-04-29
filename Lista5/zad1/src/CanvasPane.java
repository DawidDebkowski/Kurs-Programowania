import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

enum PossibleShapes {
    Triangle, Rectangle, Circle,
}

/**
 * MovableShape
 */
interface MovableShape {
    public void handleCreationResize(double mX, double mY);
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
                    selectedShape.handleCreationResize(event.getX(), event.getY());
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
                MTriangle triangle = new MTriangle(x, y, Color.FORESTGREEN);
                shape = triangle;
                break;
            case PossibleShapes.Rectangle:
                MovableRect rect = new MovableRect(x, y, Color.CYAN);
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
