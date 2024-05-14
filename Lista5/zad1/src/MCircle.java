import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
/*
 * Klasa figury koło
 */
public class MCircle extends Circle implements MShape, SaveableShape {
    public static final MShapeTypes shapeType = MShapeTypes.Circle;
    private double startX;

    public double getStartX() {
        return startX;
    }

    private double startY;

    public double getStartY() {
        return startY;
    }

    private double width; //szerokosc prostokąta na ktorym zostalo stworzone koło

    public double getWidth() {
        return width;
    }

    private double height; //wysokosc prostokąta na ktorym zostalo stworzone koło

    public double getHeight() {
        return height;
    }

    /**
     * Tworzy klasę Circle z promieniem długości 0 na podanej pozycji i o podanym kolorze.
     * @see Circle
     * @param startX pozycja startowa X
     * @param startY pozycja startowa Y
     * @param paint kolor
     */
    public MCircle(double startX, double startY, Paint paint) {
        super(0, paint);

        setCenterX(startX);
        setCenterY(startY);

        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public void handleCreationResize(double mouseX, double mouseY) {
        width = mouseX - startX;
        height = mouseY - startY;
        setCenterX(startX + width / 2); //centrum w środku prostokąta tworzącego
        setCenterY(startY + height / 2); //centrum w środku prostokąta tworzącego
        setRadius(Math.min(Math.abs(width), Math.abs(height)) / 2); //promień długośći koła wpisanego w kwadrat
    }

    public void addX(double dx) {
        setCenterX(getCenterX() + dx);
    }

    public void addY(double dy) {
        setCenterY(getCenterY() + dy);
    }

    @Override
    public Boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(sceneToLocal(x, y));
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
        return getCenterX() - width / 2;
    }

    @Override
    public double getY() {
        return getCenterY() - height / 2;
    }
}