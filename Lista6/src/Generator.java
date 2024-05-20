import java.util.Random;

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
}
