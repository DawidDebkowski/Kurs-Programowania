import java.util.List;
import javafx.scene.paint.Color;

/**
 * Klasa odpowiadająca za funkcjonalność kafelka
 */
public class CellRunnable implements Runnable, IActiveListener{
    private int row;
    private int column;
    private MGridPane pane;
    private GridCell cell;
    private double chance;
    private double delay;
    private boolean isActive = true;
    /**
     * Tworzy funkcję dla wątku z podanymi parametrami
     * 
     * @param pane
     * @param cell
     * @param k
     * @param p
     * @param r
     * @param c
     */
    public CellRunnable(MGridPane pane, GridCell cell, double k, double p, int r, int c) {
        this.pane = pane;
        this.cell = cell;
        this.chance = p;
        this.delay = k;
        this.row = r;
        this.column = c;
    }

    @Override
    public void run() {
        while(true) {
            if (Generator.Generator.nextDouble() < chance) {
                changeToRandom();
            } else {
                changeToNeighbours();
            }

            try {
                Thread.sleep(Math.round(Generator.nextDoubleBounds(delay)));

                synchronized(this) {
                    while(!isActive) {
                        System.err.println("waiting");
                        
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // branie koloru z sąsiadów nie może zostać przerwane
    private void changeToNeighbours() {
        
        // wez kolory sąsiadów
        
        // oblicz średni kolor i go ustaw
        List<Color> colors = pane.getNeighbouringColors(row, column);
        synchronized (cell) {
            System.out.println("StartCell: " + row + " " + column);
            
            Color newColor = getAverageColor(colors);
            
            cell.setBackgroundColor(newColor);
            System.out.println("EndCell: " + row + " " + column);
        }

    }

    private Color getAverageColor(List<Color> colors) {
        int n = colors.size();
        double red = 0;
        double green = 0;
        double blue = 0;
        for (Color color : colors) {
            red += color.getRed();
            green += color.getGreen();
            blue += color.getBlue();
        }
        return Color.color(red / n, green / n, blue / n);
    }

    private void changeToRandom() {
        cell.setBackgroundColor(Generator.getRandomColor());
    }

    @Override
    public void onActiveChanged(boolean newActive) {
        isActive = newActive;
    }
}
