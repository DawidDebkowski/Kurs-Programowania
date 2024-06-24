import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Rysuje drzewo binarne na podstawie jego napisu.
 * 
 * Umieszcza teksty, koła i linie w przestrzeni (x,y) na podstawie miejsca
 * danego węzła w drzewie binarnym.
 */
public class TreeVisualizer extends Pane {
    // stałe parametry do łatwego dostosowania i stylizowania
    private final double CIRCLE_RADIUS = 23;
    private final Color CIRCLE_COLOR = Color.web("#57211D");

    private final double OFFSET_Y = 30;
    private final double HEIGHT_DIVISOR = 7;
    private final Color TEXT_COLOR = Color.WHITE;
    private String lastFormat = "()";

    /**
     * Podstawowy konstruktor. Tworzy Pane oraz ustawia rysowanie drzewa od nowa
     * przy zmianie wielkości okna
     */
    public TreeVisualizer() {
        super();
        this.widthProperty().addListener((arg) -> {
            visualizeTree(lastFormat);
        });
        this.heightProperty().addListener((arg) -> {
            visualizeTree(lastFormat);
        });
    }

    /**
     * Rozpoczyna tworzenie drzewa. Usuwa z formatu ":".
     * Następnie wywołuje funkcję drawNode od pierwszego węzła
     * 
     * @param format drzewo binarne w postaci String
     */
    public void visualizeTree(String format) {
        this.getChildren().clear();

        lastFormat = format;
        format = format.replaceAll(":", "");

        drawNode(format, 1, 0);
    }

    /**
     * Zwraca indeks początku prawego poddrzewa.
     * Indeks będzie wskazywał na pierwszy znak klucza prawego poddrzewa.
     * 
     * Ponieważ substring zaczyna się od "(" musimy znaleźć odpowiadający mu ")",
     * którego szukamy poprzez sprawdzanie głębokości nawiasów.
     * 
     * @param substring substring do znalezienia prawego poddrzewa
     * @return indeks prawego poddrzewa
     */
    private int getRightIndex(String substring) {
        int depth = 0;
        for (int i = 0; i < substring.length(); i++) {
            Character c = substring.charAt(i);
            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            }

            if (depth == 0 && i + 1 < substring.length()) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Rysuje koło z kluczem korzenia aktualnego drzewa,
     * a następnie lewe i prawe poddrzewo poprzez wywołanie rekurencyjne.
     * Dzieci rysują linię do swojego rodzica.
     * 
     * Dzieli przestrzeń na kolejne głębokości w odstępach wysokość/HEIGHT_DIVISOR.
     * Każdą głębokość dzieli na 2^głębokość miejsc, na których rysuje węzły.
     * 
     * @param tree  napis drzewa w uproszczonej postaci (tylko z "()" oraz kluczy)
     * @param index indeks węzła w reprezentacji tablicy jednowymiarowej drzewa
     *              binarnego, gdzie korzeń jest na miejscu 1, lewy syn 2*indeks,
     *              prawy syn 2*indeks + 1
     * @param depth aktualna głębokość
     */
    public void drawNode(String tree, int index, int depth) {
        // usuwa zewnętrzne nawiasy
        tree = tree.substring(1, tree.length() - 1); // + ;

        // jeżeli węzeł był pusty, nie rysuj
        if (tree.length() < 1)
            return;

        // znajduje wartość węzła, która musi się kończyć "("
        String value = tree.substring(0, tree.indexOf("("));

        // oblicza jaki numer węzeł ma "od lewej" na swojej głębokości
        int rowIndex = index - (int) Math.pow(2, depth) + 1;
        // miejsce w przestrzeni na dany węzeł
        double x = rowIndex * this.getWidth() / (Math.pow(2, depth) + 1);
        double y = depth * this.getHeight() / HEIGHT_DIVISOR + OFFSET_Y;
        // jeżeli miał rodzica to rysuje do niego linię obliczając jego koordynaty
        if (depth != 0) {
            // położenie w przestrzeni rodzica, index / 2 to zawsze będzie rodzic ze względu
            // na dzielenie całkowite
            rowIndex = (index) / 2 - (int) Math.pow(2, depth - 1) + 1;
            double parentX = rowIndex * this.getWidth() / (Math.pow(2, depth - 1) + 1);
            double parentY = (depth - 1) * this.getHeight() / HEIGHT_DIVISOR + OFFSET_Y;

            Line line = new Line(parentX, parentY, x, y);
            this.getChildren().add(0, line);
        }
        // stworzenie części wizualnej
        Circle circle = new Circle(x, y, CIRCLE_RADIUS, CIRCLE_COLOR);
        Text text = new Text(x - CIRCLE_RADIUS / 2, y, value);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(TEXT_COLOR);
        text.setWrappingWidth(CIRCLE_RADIUS);
        this.getChildren().addAll(circle, text);

        // usuwa wartość aktualnego węzła z drzewa
        tree = tree.substring(tree.indexOf("("));

        // jeżeli nie ma prawego poddrzewa (nawet pustego) to nie rysuje dalej
        int sRightIndex = getRightIndex(tree);
        if (sRightIndex == -1) {
            return;
        }
        System.out.println("v: " + value + " t: " + tree);
        drawNode(tree.substring(0, sRightIndex), 2 * index, depth + 1); // lewy
        drawNode(tree.substring(sRightIndex, tree.length()), 2 * index + 1, depth + 1); // prawy

    }
}
