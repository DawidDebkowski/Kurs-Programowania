import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javafx.scene.paint.Color;

/**
 * Klasa odpowiadająca za funkcjonalność kafelka
 */
public class CellRunnable implements Runnable {
    private int row;
    private int collumn;
    private MGridPane pane;
    private GridCell cell;
    private double chance;
    private double delay;

    /** Tworzy funckję dla wątku z podanymi parametrami
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
        this.collumn = c;
    }

    @Override
    public void run() {
        for (int j = 0; j < 500; j++) {
            System.out.println("Start: " + cell.name);
            if (Generator.Generator.nextDouble() < chance) {
                changeToRandom();
            }
            else {
                changeToNeighbours();
            }
            System.out.println("End: " + cell.name);
            
            try {
                Thread.sleep(Math.round(Generator.nextDoubleBounds(delay)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //branie koloru z sąsiadów nie może zostać przerwane
    private synchronized void changeToNeighbours() {
        Color up = pane.askColor(row, collumn+1);
        Color down = pane.askColor(row, collumn-1);
        Color right = pane.askColor(row+1, collumn);
        Color left = pane.askColor(row-1, collumn);
        List<Color> colors = new ArrayList<Color>();
        for (Color c : new Color[]{up, down, right, left}) {
            if(c != null) {
                colors.add(c);
            }
        }
        Color newColor = getAverageColor(colors)
        cell.setBackgroundColor(newColor);
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
        return Color.color(red/n, green/n, blue/n);
    }

    private synchronized void changeToRandom() {
        cell.setBackgroundColor(Generator.getRandomColor());
    }

    private int getAverage(double a, double b, double c, double d)
    {
        return (int)Math.round((a+b+c+d)/4*255);
    }
}
