import java.util.Random;
import java.util.random.RandomGenerator;

import javafx.scene.paint.Color;

public class CellThread implements Runnable {
    private GridCell cell;
    private double chance;
    private double delay;

    public CellThread(GridCell cell, double k, double p) {
        this.cell = cell;
        this.chance = p;
        this.delay = k;
    }

    @Override
    public void run() {
        try {
            int i=100;
            for(int j=0;j<50;j++) {
                // if(Generator.Generator.nextDouble() < chance) {
                    {
                    cell.setBackgroundColor(Color.rgb(100, i, i));
                    i=i*10;
                    i%=255;
                }
                try {
                    Thread.sleep(Math.round(Generator.nextDoubleBounds(delay*0.5, delay*1.5)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(cell.name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
