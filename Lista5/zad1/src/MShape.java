import javafx.scene.paint.Paint;

/**
 * Ogólny interfesj figury, którą można tworzyć, poruszać, skalować i obracać
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
