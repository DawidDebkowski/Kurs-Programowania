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
    private double chance; // p
    private double delay; // k
    // zmienna odpowiadająca za wstrzymanie wątku
    volatile private boolean isActive = true;
    // zmienna warunkowa działania wątku
    volatile private boolean isWorking = true;

    /**
     * Tworzy wątek z podanymi parametrami
     * 
     * @param pane plansza
     * @param cell przypisany kafelek
     * @param k interwał
     * @param p szansa na zmianę
     * @param r wiersz
     * @param c kolumna
     */
    public CellThread(MGridPane pane, GridCell cell, double k, double p, int r, int c) {
        this.pane = pane;
        this.cell = cell;
        setParameters(k, p, r, c);
    }

    /**
     * Funkcja do zmiany parametrów
     */
    public void setParameters(double k, double p, int r, int c) {
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
        while (isWorking) {
            try {
                //od razu po aktywowaniu (isActive) wątek zacznie pracować
                Thread.sleep(Math.round(Generator.nextDoubleBounds(delay)));

                //nie musimy dokonywać niepotrzebnej synchronizacji jeżeli wątek jest aktywny
                if (!isActive) {
                    synchronized (this) {
                        while (!isActive && isWorking) {
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //zmiana koloru
            if (Generator.Generator.nextDouble() < chance) {
                changeToRandom();
            } else {
                changeToNeighbours();
            }
        }
    }

    // synchronizuje cały grid żeby uniknąć kolizji brania koloru
    private void changeToNeighbours() {
        synchronized (pane) {
            System.out.println("Start: " + this.getName());
            List<Color> colors = pane.getNeighbouringColors(row, column);
            Color newColor;

            // jeżeli nie ma sąsiadów nie zmienia koloru
            if (colors.size() != 0) {
                newColor = getAverageColor(colors);
                cell.setBackgroundColor(newColor);
            }

            System.out.println("End: " + this.getName());
        }

    }

    //wylicza średni kolor z listy kolorów
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

    /**
     *  Wstrzymuje lub wznawia pracę wątku
     */
    public void setActive(boolean newActive) {
        isActive = newActive;
    }

    /**
     * Całkowicie kończy pracę wątku
     */
    public void shutdown() {
        isWorking = false;
    }
}
