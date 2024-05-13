import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

enum PossibleShapes {
    Triangle("T"), Rectangle("R"), Circle("C");

    public String saveString;

    PossibleShapes(String saveString) {
        this.saveString = saveString;
    }
}

/**
 * MShape
 */
interface MShape {
    /**
     * Zmienia wielkość figury na podstawie początkowej i aktualnej pozycji myszki
     * @param mouseX - pozycja myszki x
     * @param mouseY - pozycja myszki y
     */
    public void handleCreationResize(double mouseX, double mouseY);

    public void setStroke(Paint paint);

    public void setFill(Paint paint);

    public Boolean isHit(double x, double y);

    public void addX(double dx);

    public void addY(double dy);

    public void addWidth(double d);

    public void addHeight(double d);

    public void rotate(double degrees);

    public void setScaleX(double scaleX);

    public void setScaleY(double scaleY);
}

/**
 * SaveableShape
 */
interface SaveableShape {
    /**
     * zwraca typ figury
     */
    public PossibleShapes getShapeType();

    public double getStartX();

    public double getStartY();

    public double getWidth();

    public double getHeight();

    public double getX();

    public double getY();

    public double getScaleX();

    public double getScaleY();

    public double getRotate();

    public Paint getFill();
}

/**
 * CanvasPane
 * 
 */
public class CanvasPane extends Pane {
    public static Color activeColor = Color.LEMONCHIFFON;

    private PossibleShapes chosenShape;
    private MShape selectedShape;
    private MShape activeShape;

    public PopupMenu popupMenu = new PopupMenu(this);

    public CanvasPane() {
        this.setStyle("-fx-background-color: black;");
        chosenShape = PossibleShapes.Circle;
        activeShape = null;

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedShape = null;
                if (activeShape == null) {
                    createShape(event.getX(), event.getY());
                } else {
                    if (!activeShape.isHit(event.getSceneX(), event.getSceneY())) {
                        deactiveShape();
                    }
                }
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (selectedShape != null && activeShape == null) {
                    selectedShape.handleCreationResize(event.getX(), event.getY());
                }
            }

        });
    }

    public MShape getActiveShape() {
        return activeShape;
    }

    public void setActiveShape(MShape shape) {
        if (shape == activeShape)
            return;
        deactiveShape();
        activeShape = shape;
    }

    private void deactiveShape() {
        if (activeShape != null) {
            activeShape.setStroke(Color.BLACK);
            activeShape = null;
            popupMenu.hide();
        }
    }

    public void setShape(PossibleShapes newShape) {
        chosenShape = newShape;
    }

    public void createShape(PossibleShapes shape, double startX,
            double startY, double x, double y, double width, double height,
            double scaleX, double scaleY, double rotate, Paint colorPaint) {
        chosenShape = shape;
        createShape(startX, startY);
        MShape MShape = (MShape) selectedShape;
        selectedShape.handleCreationResize(width + startX, height + startY);
        MShape.addX(x - startX); // dodanie przesuniecia od pozycji poczatkowej
        MShape.addY(y - startY);
        MShape.setScaleX(scaleX);
        MShape.setScaleY(scaleY);
        MShape.rotate(rotate);
        MShape.setFill(colorPaint);
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
            selectedShape = (MShape) shape;
            ActiveMoveHandler activeMoveHandler = new ActiveMoveHandler(this);
            shape.setStroke(Color.BLACK);
            shape.setOnMouseClicked(activeMoveHandler);
            shape.setOnMousePressed(activeMoveHandler);
            shape.setOnMouseDragged(activeMoveHandler);
            shape.setOnScroll(new ActivableScrollHandler(this));
            this.getChildren().addAll(shape);
        }
    }
}
