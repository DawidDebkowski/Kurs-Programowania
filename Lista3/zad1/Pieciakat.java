public class Pieciakat extends Figura{
    double a;

    public Pieciakat(double a)
    {
        this.a = a;
    }

    public double obliczObwod() {
        return a*5;
    }
    
    public double obliczPole() {
        return a*a/4*Math.sqrt(25+10*Math.sqrt(5));
    }

    public String podajNazwe()
    {
        return "Pieciokat";
    }
}
