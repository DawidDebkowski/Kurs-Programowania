public class TemperaturaTest {
    Temperatura co;

    public static void main(String[] args) {
        Temperatura b = new Temperatura();
        Temperatura c = new Temperatura();

        b.a = c.a = 3;
        b.a = Integer.parseInt(args[0]);
        try {
            System.out.println(b.geta());
        }
        catch(MojException e) {
            System.out.println(e);
        }
    }
}
/*
 *  Garbage Collector:
 *  java fajnie (programista debil)
 *  cpp okej    (inteligentny programista (musi sie meczyc))
 * 
 *  Obiekty w cpp
 * 
 * 
 */