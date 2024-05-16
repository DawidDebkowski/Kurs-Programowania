import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MGridPane extends GridPane {
    GridCell[][] cells;

    public MGridPane(int n, int m, int k, int p) {
        super(n, m);
        
        cells = new GridCell[n][m];
        CreatePanes(n, m);
    }

    private void CreatePanes(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                GridCell cell = new GridCell(Color.rgb(m + 100, (i * 20) % 255, (j * 30) % 255));
                cells[i][j] = cell;
                this.add(cell, i, j);
            }
        }
    }
}
