import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

enum MShapeTypes {
    Triangle("T"), Rectangle("R"), Circle("C");

    public String saveString;

    MShapeTypes(String saveString) {
        this.saveString = saveString;
    }
}

/**
 * MShape
 */
interface MShape {
    /**
     * Zmienia wielkość figury na podstawie początkowej i aktualnej pozycji myszki
     * 
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
    public MShapeTypes getShapeType();

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

    private MShapeTypes chosenShape;
    private MShape selectedShape;
    private MShape activeShape;

    public PopupMenu popupMenu = new PopupMenu(this);

    /**
     * Podstawowy konstruktor
     * 
     * Ustawia logikę klikania i przeciągania po planszy.
     */
    public CanvasPane() {
        this.setStyle("-fx-background-color: black;");
        chosenShape = MShapeTypes.Circle;
        activeShape = null;

        setupHandlers();
    }

    private void setupHandlers() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedShape = null;
                if (activeShape == null) {
                    createShape(event.getX(), event.getY());
                } else {
                    if (!activeShape.isHit(event.getSceneX(), event.getSceneY())) {
                        deactivateShape();
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

    /**
     * @return aktualnie aktywny kształt
     */
    public MShape getActiveShape() {
        return activeShape;
    }

    /**
     * Procedura deaktywuje poprzednią figurę, a potem ustawią nową
     * jako aktywną i ustawia jej efekty wizualne aktywnej figury
     * 
     * @param shape nowa figura do ustawienia jako aktywna
     *
     */
    public void setActiveShape(MShape shape) {
        if (shape == activeShape)
            return;
        deactivateShape();
        activeShape = shape;
        activeShape.setStroke(CanvasPane.activeColor);
    }

    /**
     * Procedura usuwa efekty wizualne związane z aktywną figurą i przypisuje "null"
     * jako aktywną figurę
     * Wyłącza także menu
     */
    private void deactivateShape() {
        if (activeShape != null) {
            activeShape.setStroke(Color.BLACK);
            activeShape = null;
            popupMenu.hide();
        }
    }

    /**
     * Ustawia typ kształtu do narysowania
     * 
     * @param newShape kształt do rysowania
     */
    public void setShapeType(MShapeTypes newShape) {
        chosenShape = newShape;
    }

    /**
     * Procedura tworząca figurę na podstawie zapisu
     * Używa prywatnego createShape(), a potem przesuwa, skaluje i obraca figurę
     * @param shape typ figury
     * @param startX pozycja startowa X
     * @param startY pozycja startowa Y
     * @param x aktualna pozycja x
     * @param y aktualna pozycja y
     * @param width szerokość prostokąta tworzącego
     * @param height wysokość prostokąta tworzącego
     * @param scaleX skala X
     * @param scaleY skala Y
     * @param rotate kąt obrotu 
     * @param colorPaint kolor wypełnienia
     */
    public void createShape(MShapeTypes shape, double startX,
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
            case MShapeTypes.Triangle:
                MTriangle triangle = new MTriangle(x, y, Color.FORESTGREEN);
                shape = triangle;
                break;
            case MShapeTypes.Rectangle:
                MRectangle rect = new MRectangle(x, y, Color.CYAN);
                shape = rect;
                break;
            case MShapeTypes.Circle:
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
            shape.setOnScroll(new ActiveScrollHandler(this));
            this.getChildren().addAll(shape);
        }
    }
}
