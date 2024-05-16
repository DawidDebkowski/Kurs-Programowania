import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MGridPane extends GridPane {
    Pane[][] panes;

    public MGridPane(int n, int m, int k, int p) {
        super(n, m);
        
        panes = new Pane[n][m];
        CreatePanes(n, m);
    }

    private void CreatePanes(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Pane pan = new Pane();
                pan.setMinSize(50, 50);
                pan.setBackground(new Background(
                        new BackgroundFill(Color.rgb(m + 100, (i * 20) % 255, (j * 30) % 255), null, new Insets(5))));
                panes[i][j] = pan;
                this.add(pan, i, j);
            }
        }
    }
}
