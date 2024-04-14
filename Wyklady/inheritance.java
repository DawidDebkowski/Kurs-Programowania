import java.util.random.*;
class Baza {
    public Baza(String n)
    {
        System.out.println("baza" + n);
    }
    protected void a()
    {
        System.out.println("a");

    }
}

class Potomna extends Baza {
    public Potomna(){
        super("potomna");
    }
    public void b()
    {
        System.out.println("b");

    }
}

/**
 * Krzysiu
 */
interface Krzysiu {
    public static final int stala = 0;
    public void CoMiSieUdalo();
    public void Dodawanie(int a, int b);
}

class AlgebraicznyKrzysiu implements Krzysiu {
    public void CoMiSieUdalo(){
        System.out.println("8+7=13");
    }
    public void Dodawanie(int a, int b) {
        System.out.println("Algebraiczny: 13" + stala);
    }
}
class PatyczakKrzysiu implements Krzysiu {
    public void CoMiSieUdalo(){
        System.out.println("nic");
    }
    public void Dodawanie(int a, int b) {
        System.out.println("Patyczak: " + (a+b+ java.util.random.RandomGenerator(0,3)));
    }
}

public class inheritance {
    public static void main(String[] args) {
        Baza a = new Potomna();
        Potomna b = new Potomna();
        
        Krzysiu krzysie[] = new Krzysiu[2];
        krzysie[0] = new AlgebraicznyKrzysiu();
        krzysie[1] = new PatyczakKrzysiu();

        for (Krzysiu krzysiu : krzysie) {
            for(int i=0;i<10;i++)
                krzysiu.Dodawanie(i, 8);
        }
    }
}
