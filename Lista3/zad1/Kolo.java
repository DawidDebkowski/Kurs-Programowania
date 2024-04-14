public class Kolo extends Figura
{
    double promien;

    public Kolo(double promien)
    {
        this.promien = promien;
    }

    public double obliczObwod()
    {
        return 2 * Math.PI * promien;
    }

    public double obliczPole()
    {
        return Math.PI * promien * promien;
    }

    public String podajNazwe()
    {
        return "Koło";
    }
}
