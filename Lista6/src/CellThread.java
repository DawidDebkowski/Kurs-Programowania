import javafx.scene.paint.Color;

public class CellThread implements Runnable {
    private GridCell cell;

    public CellThread(GridCell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        int i=100;
        while (true) {
            cell.setBackgroundColor(Color.rgb(100, i, i));
            i=i*10;
            i%=255;
            // System.err.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
