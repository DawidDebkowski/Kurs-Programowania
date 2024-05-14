import javafx.scene.paint.Paint;

/**
 * Ogólny interfejs figury, którą można tworzyć, poruszać, skalować i obracać
 */
public interface MShape {
    /**
     * Zmienia wielkość figury na podstawie początkowej i aktualnej pozycji myszki
     * 
     * @param mouseX - pozycja myszki x
     * @param mouseY - pozycja myszki y
     */
    public void handleCreationResize(double mouseX, double mouseY);

    /**
     * Ustawia kolor obramowania
     * 
     * @param paint kolor
     */
    public void setStroke(Paint paint);

    /**
     * Ustawia kolor wypełnienia
     * 
     * @param paint kolor
     */
    public void setFill(Paint paint);

    /**
     * Sprawdza czy trafiliśmy w figurę na danym x,y
     * 
     * @param x pozycja x względem sceny
     * @param y pozycja y względem sceny
     * @return Prawda gdy trafiona wpp fałsz
     */
    public Boolean isHit(double x, double y);

    /**
     * Przesuwa figurę
     * 
     * @param dx przesunięcie
     */
    public void addX(double dx);

    /**
     * Przesuwa figurę
     * 
     * @param dy przesunięcie
     */
    public void addY(double dy);

    /**
     * Zwiększa szerokość figury
     * 
     * @param d zwiększenie
     */
    public void addWidth(double d);

    /**
     * Zwiększa wysokość figury
     * 
     * @param d zwiększenie
     */
    public void addHeight(double d);

    /**
     * Obraca figurę o podany kąt
     * 
     * @param degrees kąt
     */
    public void rotate(double degrees);

    /**
     * Zmienia skalę X figury
     */
    public void setScaleX(double scaleX);

    /**
     * Zmienia skalę Y figury
     */
    public void setScaleY(double scaleY);
}
