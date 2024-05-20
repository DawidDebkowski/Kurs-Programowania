import javafx.scene.paint.Paint;

/**
 * Interfejs figury którą można zapisać
 */
public interface SaveableShape {
    /**
     * zwraca typ figury
     * 
     * @return typ figury
     * @see MShapeTypes
     */
    public MShapeTypes getShapeType();

    /**
     * zwraca pozycję startową X
     * 
     * @return pozycja startowa X
     */
    public double getStartX();

    /**
     * zwraca pozycję startową Y
     * 
     * @return pozycja startowa Y
     */
    public double getStartY();

    /**
     * zwraca szerokość prostokąta tworzącego figurę
     * 
     * @return szerokość prostokąta tworzącego figurę
     */
    public double getWidth();

    /**
     * zwraca wysokość prostokąta tworzącego figurę
     * 
     * @return wysokość prostokąta tworzącego figurę
     */
    public double getHeight();

    /**
     * zwraca aktualną pozycję X
     * 
     * @return aktualna pozycja X
     */
    public double getX();

    /**
     * zwraca aktualną pozycję Y
     * 
     * @return aktualna pozycja Y
     */
    public double getY();

    /**
     * zwraca aktualną skalę X
     * 
     * @return aktualna skala X
     */
    public double getScaleX();

    /**
     * zwraca aktualną skalę Y
     * 
     * @return aktualna skala Y
     */
    public double getScaleY();

    /**
     * zwraca kąt o jaki figura została obrócona
     * 
     * @return kąt o jaki figura została obrócona
     */
    public double getRotate();

    /**
     * zwraca aktualny kolor wypełnienia
     * 
     * @return aktualny kolor wypełnienia
     */
    public Paint getFill();
}
