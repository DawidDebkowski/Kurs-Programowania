import java.util.List;

import javafx.scene.paint.Color;

/**
 * Klasa odpowiadająca za funkcjonalność kafelka
 */
public class CellThread extends Thread implements IActiveListener {
    private int row;
    private int column;
    private MGridPane pane;
    private GridCell cell;
    private double chance;
    private double delay;
    volatile private boolean isActive = true;
    volatile private boolean shutdown = false;

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
    public CellThread(MGridPane pane, GridCell cell, double k, double p, int r, int c) {
        this.pane = pane;
        this.cell = cell;
        this.chance = p;
        this.delay = k;
        this.row = r;
        this.column = c;
    }

    /**
     * Metoda głownej pętli wątku
     */
    @Override
    public void run() {
        while (!shutdown) {
            try {
                Thread.sleep(Math.round(Generator.nextDoubleBounds(delay)));

                if(!isActive) {
                    synchronized (this) {
                        while (!isActive && !shutdown) {    
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Generator.Generator.nextDouble() < chance) {
                changeToRandom();
            } else {
                changeToNeighbours();
            }
        }
    }

    //synchronizuje cały grid żeby uniknąć kolizji brania koloru
    private void changeToNeighbours() {
        synchronized (pane) {
            System.out.println("Start: " + this.getName());
            List<Color> colors = pane.getNeighbouringColors(row, column);
            Color newColor;

            //jeżeli nie ma sąsiadów nie zmienia koloru 
            if (colors.size() != 0) {
                newColor = getAverageColor(colors);
                cell.setBackgroundColor(newColor);
            }

            System.out.println("End: " + this.getName());
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

    public void shutdown() {
        shutdown = true;
    }
}
