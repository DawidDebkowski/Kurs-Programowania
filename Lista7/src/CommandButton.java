import javafx.scene.control.Button;

/**
 * Klasa ujednolicająca przyciski komend.
 * Przycisk będzie zajmował całą możliwą szerokość.
 */
public class CommandButton extends Button{

    /**
     * Ustawia wskazany tekst na przycisku. Przycisk będzie zajmował całą możliwą szerokość.
     * @param text
     */
    public CommandButton(String text) {
        super(text);
        this.setPrefWidth(Integer.MAX_VALUE);
    }
}
