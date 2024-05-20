import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Klasa zarządzająca planszą
 */
public class MGridPane extends GridPane {
    private GridCell[][] cells;
    private Thread[][] threads;
    private int rows;
    private int columns;
    /**
     * Konstruktor tworzy plansze losowych kolorów i przypisuje im wątki.
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

    /** Zwraca kolor danej komórki
     * @param i wiersz
     * @param j kolumna
     * @return kolor i,j-tej komórki
     */
    public Color askColor(int i, int j) {
        System.out.println(i + " " + j);
        return cells[Math.floorMod(i, rows)][Math.floorMod(j, columns)].getBackgroundColor();
    }
    
    private void createPanes(double k, double p) {
        cells = new GridCell[rows][columns];
        threads = new Thread[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GridCell cell = new GridCell(Generator.getRandomColor(), "" + i + " " + j);
                cells[i][j] = cell;
                this.add(cell, j, i); //ma kolumna, wiersz

                CellRunnable hi = new CellRunnable(this, cell, k, p, i, j);
                Thread t = new Thread(hi);
                threads[i][j] = t;
            }
        }
    }
}
