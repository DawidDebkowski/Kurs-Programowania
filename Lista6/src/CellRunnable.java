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
        for (int j = 0; j < 50; j++) {
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
        Color newColor = Color.rgb(getAverage(up.getRed(), down.getRed(), right.getRed(), left.getRed()),
        getAverage(up.getGreen(), down.getGreen(), right.getGreen(), left.getGreen()),
        getAverage(up.getBlue(), down.getBlue(), right.getBlue(), left.getBlue()));
        cell.setBackgroundColor(newColor);
    }

    private synchronized void changeToRandom() {
        cell.setBackgroundColor(Color.rgb(Generator.Generator.nextInt(255), Generator.Generator.nextInt(255), Generator.Generator.nextInt(255)));
    }

    private int getAverage(double a, double b, double c, double d)
    {
        return (int)Math.round((a+b+c+d)/4*255);
    }
}
