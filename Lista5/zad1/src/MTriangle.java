import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * Klasa odpowiadajaca za figure trojkata
 */

public class MTriangle extends Polygon implements MShape, SaveableShape {
    public static final MShapeTypes shapeType = MShapeTypes.Triangle;
    private double startX;

    public double getStartX() {
        return startX;
    }

    private double startY;

    public double getStartY() {
        return startY;
    }

    private double width;

    public double getWidth() {
        return width;
    }

    private double height;

    public double getHeight() {
        return height;
    }

    /**
     * Tworzy pustą klasę polygon we wskazanym miejscu startowym i kolorze
     * @param startX miejsce tworzenia x
     * @param startY miejsce tworzenia y
     * @param paint  kolor wypełnienia
     */
    public MTriangle(double startX, double startY, Paint paint) {

        super();

        this.setFill(paint);
        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void handleCreationResize(double mouseX, double mouseY) {
        width = mouseX - startX;
        height = mouseY - startY;

        //trójkąt równoramienny wpisany w prostokąt 
        getPoints().removeAll(getPoints());
        getPoints().addAll(new Double[] {
                width / 2 + startX, startY,
                startX, height + startY,
                width + startX, height + startY,
        });
    }

    @Override
    public Boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(sceneToLocal(x, y)); // Inne nie działają, nie wiem czemu
    }

    @Override
    public void addX(double dx) {
        setTranslateX(getTranslateX() + dx);
    }

    @Override
    public void addY(double dy) {
        setTranslateY(getTranslateY() + dy);
    }

    @Override
    public void addWidth(double d) {
        setScaleX(getScaleX() + d * 0.01);
    }

    @Override
    public void addHeight(double d) {
        setScaleY(getScaleY() + d * 0.01);
    }

    @Override
    public void rotate(double degrees) {
        setRotate(getRotate() + degrees);
    }

    @Override
    public MShapeTypes getShapeType() {
        return shapeType;
    }

    @Override
    public double getX() {
        return getTranslateX() + startX;
    }

    @Override
    public double getY() {
        return getTranslateY() + startY;
    }
}
