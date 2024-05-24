import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Klasa odpowiadająca za pojedynczy kafelek
 */
public class GridCell extends Pane {
    public String name;
    private boolean isActive = true;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        int inset = 0;
        if(!isActive)
            inset = 10;
        setInsets(inset);
    }

    private void setInsets(int inset) {
        // setBackground(new Background(new BackgroundFill(getBackgroundColor(), null, new Insets(inset))));
        // setPadding(new Insets(inset));
        if(!isActive)
            setStyle("-fx-border-color: rgb(0, 0, 0);" + "-fx-border-width: 1;");
        else
            setStyle("");
    }

    /**
     * Tworzy Pane o wielkości 50x50 i podanym kolorze oraz przypisuje numer
     * @param color
     * @param name
     */
    public GridCell(Color color, String name) {
        this.name = name;
        this.setPrefSize(1000, 1000);
        this.setBackground(new Background(
                new BackgroundFill(color, null, new Insets(5))));
    }

    /**
     * Ustawia kolor tła
     * @param color nowy kolor
     */
    public /*synchronized*/ void setBackgroundColor(Color color) {
        Platform.runLater(() -> this.setBackground(new Background(
                new BackgroundFill(color, null, null))));
    }

    /** Zwraca kolor tła
     * @return kolor
     */
    public /*synchronized*/ Color getBackgroundColor() {
        if(!isActive) {
            return null;
        }
        return (Color)this.getBackground().getFills().getFirst().getFill();
    }
}
