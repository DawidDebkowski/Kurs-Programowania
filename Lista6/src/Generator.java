import java.util.Random;

import javafx.scene.paint.Color;

public class Generator extends Random {
    public static Random Generator;

    public Generator() {
        if (Generator == null)
            Generator = this;
    }

    public static double nextDoubleBounds(double a) {
        // System.out.println((Generator.nextDouble() + 0.5)*a);
        return (Generator.nextDouble() + 0.5)*a;
    }

    public static Color getRandomColor()
    {
        return Color.rgb(Generator.nextInt(255), Generator.nextInt(255), Generator.nextInt(255));
    }
}
