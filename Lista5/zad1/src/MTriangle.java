import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * Klasa odpowiadajaca za figure trojkata
 */

public class MTriangle extends Polygon implements MovableShape, ActivableShape {
    private double startX;
    private double startY;

    /**
     * @param startX miejsce tworzenia x
     * @param startY miejsce tworzenia y
     * @param paint kolor wypełnienia
     */
    public MTriangle(double startX, double startY, Paint paint) {
        super();

        this.setFill(paint);
        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void handleCreationResize(double mouseX, double mouseY) {
        double width = mouseX - startX;
        double height = mouseY - startY;

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
        setLayoutX(getLayoutX() + dx);
    }

    @Override
    public void addY(double dy) {
        setLayoutY(getLayoutY() + dy);
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
}
