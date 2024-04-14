public class Szesciakat extends Figura{
    double a;
    public Szesciakat(double a)
    {
        this.a = a;
    }
    public double obliczObwod() {
        return a*6;
    }
    
    public double obliczPole() {
        return 3*a*a*Math.sqrt(3)/2;
    }

    public String podajNazwe()
    {
        return "Szesciokat";
    }
}
