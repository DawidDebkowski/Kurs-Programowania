import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Klasa zarządzająca planszą
 */
public class MGridPane extends GridPane {
    private GridCell[][] cells;
    private Thread[][] threads;

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

        createPanes(n, m, k, p);
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
     * @param i kolumna
     * @param j wiersz
     * @return kolor i,j-tej komórki
     */
    public Color askColor(int i, int j) {
        System.out.println(i + " " + j);
        return cells[Math.floorMod(i, cells.length)][Math.floorMod(i, cells[0].length)].getBackgroundColor();
    }
    
    private void createPanes(int n, int m, double k, double p) {
        cells = new GridCell[n][m];
        threads = new Thread[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                GridCell cell = new GridCell(Generator.getRandomColor(), "" + i + " " + j);
                cells[i][j] = cell;
                this.add(cell, i, j);

                CellThread hi = new CellThread(this, cell, k, p, i, j);
                Thread t = new Thread(hi);
                threads[i][j] = t;
            }
        }
    }

    public Button debugButton() {
        Button b = new Button("aaaaaaaa");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Paint last = Color.BLACK;
                for (int i = 0; i < threads.length; i++) {
                    for (int j = 0; j < threads[i].length; j++) {
                        // if(!last.toString().equals(cells[i][j].getBackground().getFills().getFirst().getFill().toString()))
                        {
                            System.out.println(i+" "+j);
                            System.err.println("AAAAAAAAAAAAAa");
                            System.out.println(threads[i][j].getState());
                            // cells[i][j].setFill(Color.BLACK);
                            // System.out.println(cells[i][j].getBackground().getFills().getFirst().getFill());
                        }
                        // last = cells[i][j].getBackground().getFills().getFirst().getFill();
                    }
                }
    
            }
        });
        return b;
    }
}
