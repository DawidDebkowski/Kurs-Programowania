import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Klasa odpowiadająca za pojedynczy kafelek
 */
public class GridCell extends Pane implements IActiveListener{
    //aktywność kafelka (czy brany jest pod uwagę w symulacji)
    private boolean isActive = true;

    /**
     * Zwraca czy kafelek jest aktywny
     * @return czyAktywny
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Zmienia status aktywności kafelka
     * 
     * @param isActive nowy stan aktywności
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
        updateInsets();
    }

    private void updateInsets() {
        if (!isActive)
            setStyle("-fx-border-color: rgb(0, 0, 0);" + "-fx-border-width: 1;");
        else
            setStyle("");
    }

    /**
     * Tworzy Pane o podanym kolorze
     * 
     * @param color kolor początkowy
     */
    public GridCell(Color color) {
        //aby kafelek zajmował całą dostępną mu powierzchnię
        this.setPrefSize(1000, 1000);
        //ustawia kolor tła
        setBackgroundColor(color);
    }

    /**
     * Ustawia kolor tła
     * 
     * @param color nowy kolor
     */
    public void setBackgroundColor(Color color) {
        //wątek javafx zmieni kolor tła dopiero gdy będzie mogła to zrobić
        //inaczej kafelki czasami "psują się" i przestają zmieniać kolor
        Platform.runLater(() -> this.setBackground(new Background(
                new BackgroundFill(color, null, null))));
    }

    /**
     * Zwraca kolor tła
     * 
     * @return kolor
     */
    public Color getBackgroundColor() {
        if (!isActive) {
            return null;
        }
        return (Color) this.getBackground().getFills().getFirst().getFill();
    }
}
