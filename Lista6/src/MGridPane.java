import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MGridPane extends GridPane {
    GridCell[][] cells;
    Thread[][] threads;

    public MGridPane(int n, int m, double k, double p) {
        super(n, m);

        cells = new GridCell[n][m];
        threads = new Thread[n][m];
        CreatePanes(n, m, k, p);
        Button b = new Button("aaaaaaaa");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Paint last = Color.BLACK;
                for (int i = 0; i < threads.length; i++) {
                    for (int j = 0; j < threads[i].length; j++) {
                        if(!last.toString().equals(cells[i][j].getBackground().getFills().getFirst().getFill().toString()))
                        {
                            System.out.println(i+" "+j);
                            System.err.println("AAAAAAAAAAAAAa");
                            System.out.println(threads[i][j].getState());
                            System.out.println(cells[i][j].getBackground().getFills().getFirst().getFill());
                        }
                        last = cells[i][j].getBackground().getFills().getFirst().getFill();
                    }
                }

            }
        });

        this.add(b, n+1, m+1);
        for (int i = 0; i < threads.length; i++) {
            for (int j = 0; j < threads[i].length; j++) {
                threads[i][j].setPriority(i + 1);
                threads[i][j].start();
            }
        }
    }

    private void CreatePanes(int n, int m, double k, double p) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                GridCell cell = new GridCell(Color.rgb(m + 100, (i * 20) % 255, (j * 30) % 255), "" + i + " " + j);
                cells[i][j] = cell;
                this.add(cell, i, j);

                CellThread hi = new CellThread(cell, k, p);
                Thread t = new Thread(hi);
                threads[i][j] = t;
            }
        }
    }
}
