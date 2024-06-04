import java.util.Random;

import javafx.scene.paint.Color;

public class Generator {
    public static Random Generator = new Random();

    /** Funkcja zwraca losową liczbę zmiennoprzecinkową z zakresu [0,5*multiplier, 1,5*multiplier]
     * @param a
     * @return
     */
    public static double nextDoubleBounds(double multiplier) {
        return (Generator.nextDouble() + 0.5)*multiplier;
    }

    /** Funkcja zwraca losowy kolor
     * @return losowy kolor
     */
    public static Color getRandomColor()
    {
        return Color.rgb(Generator.nextInt(255), Generator.nextInt(255), Generator.nextInt(255));
    }
}
