/**
 * Enum zawierający nazwy figur możliwych do narysowania wraz z odpowiadjącymi
 * im skrótami do pliku zapisu.
 */
public enum MShapeTypes {
    Triangle("T"), Rectangle("R"), Circle("C");

    public String saveString;

    MShapeTypes(String saveString) {
        this.saveString = saveString;
    }
}
