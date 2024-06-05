import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Klasa zarządzająca planszą
 */
public class MGridPane extends GridPane {
    //referencja do kafelków
    private GridCell[][] cells;
    //referencja do wątków
    private CellThread[][] threads;
    //wielkość planszy
    private int rows;
    private int columns;

    /**
     * Konstruktor tworzy plansze losowych kolorów i przypisuje im wątki.
     * 
     * @param n ilość kolumn
     * @param m ilość wierszy
     * @param k opóźnienie
     * @param p szansa na zmianę
     */
    public MGridPane(int n, int m, double k, double p) {
        super(n, m);
        this.setHgap(0);
        this.setVgap(0);

        this.rows = m;
        this.columns = n;
        //tworzy planszę
        createPanes(k, p);
    }

    /**
     * Rozpoczyna działanie wątków
     */
    public void startThreads() {
        for (int i = 0; i < threads.length; i++) {
            for (int j = 0; j < threads[i].length; j++) {
                threads[i][j].start();
            }
        }
    }

    /**
     * Kończy działanie wszystkich wątków.
     */
    public void stopThreads() {
        for (int i = 0; i < threads.length; i++) {
            for (int j = 0; j < threads[i].length; j++) {
                threads[i][j].shutdown();
            }
        }
    }

    /**
     * Zwraca kolor danej komórki
     * 
     * @param i wiersz
     * @param j kolumna
     * @return kolor i,j-tej komórki
     */
    public Color askColor(int i, int j) {
        //modulo daje nam torus
        return cells[Math.floorMod(i, rows)][Math.floorMod(j, columns)].getBackgroundColor();
    }

    /**
     * Odczytuje kolory sąsiadów danego kafelka
     * Jeżeli sąsiad nie jest aktywny, nie ma go na liście kolorów.
     * @param row wiersz kafelka
     * @param column kolumna kafelka
     * @return lista kolorów sąsiadów
     */
    public synchronized List<Color> getNeighbouringColors(int row, int column) {
        Color up = askColor(row, column + 1);
        Color down = askColor(row, column - 1);
        Color right = askColor(row + 1, column);
        Color left = askColor(row - 1, column);

        // sprawdza które są aktywne
        List<Color> colors = new ArrayList<Color>();
        for (Color c : new Color[] { up, down, right, left }) {
            if (c != null) {
                colors.add(c);
            }
        }
        return colors;
    }

    //tworzy planszę
    private void createPanes(double k, double p) {
        cells = new GridCell[rows][columns];
        threads = new CellThread[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GridCell cell = new GridCell(Color.WHITE);
                cells[i][j] = cell;
                this.add(cell, j, i); // Grid ma kolumna, wiersz

                CellThread thread = new CellThread(this, cell, k, p, i, j);
                threads[i][j] = thread;
                thread.setName("("+i+", "+j+")");

                cell.setOnMouseClicked(new CellClickHandler(cell, thread));
            }
        }
    }
}
