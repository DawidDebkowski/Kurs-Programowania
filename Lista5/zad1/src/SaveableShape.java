import javafx.scene.paint.Paint;

/**
 * Interfejs figury którą można zapisać
 */
public interface SaveableShape {
    /**
     * zwraca typ figury
     */
    public MShapeTypes getShapeType();

    /**
     * @return pozycja startowa X
     */
    public double getStartX();

    /**
     * @return pozycja startowa Y
     */
    public double getStartY();

    /**
     * @return szerokość prostokąta tworzącego figurę
     */
    public double getWidth();

    /**
     * @return wysokość prostokąta tworzącego figurę
     */
    public double getHeight();

    /**
     * @return aktualna pozycja X
     */
    public double getX();

    /**
     * @return aktualna pozycja Y
     */
    public double getY();

    /**
     * @return aktualna skala X
     */
    public double getScaleX();

    /**
     * @return aktualna skala Y
     */
    public double getScaleY();

    /**
     * @return kąt o jaki figura została obrócona
     */
    public double getRotate();

    /**
     * @return aktualny kolor wypełnienia
     */
    public Paint getFill();
}
