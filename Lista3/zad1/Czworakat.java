public abstract class Czworakat extends Figura
{
    public String nazwa;
    double a, b;
    double kat;
}


class Kwadrat extends Czworakat
{
    public Kwadrat(double a)
    {
        this.a = a;
        this.b = a;
        this.kat = 90;
        nazwa = "Kwadrat";
    }

    public double obliczPole()
    {
        return a * a;
    }

    public double obliczObwod()
    {
        return a * 4;
    }

    public String podajNazwe()
    {
        return nazwa;
    }
}


class Prostokat extends Czworakat
{
    public Prostokat(double a, double b)
    {
        this.a = a;
        this.b = b;
        this.kat = 90;
        nazwa = "Prostakat";
    }

    public double obliczPole()
    {
        return a * b;
    }

    public double obliczObwod()
    {
        return a * 2 + b * 2;
    }

    public String podajNazwe()
    {
        return nazwa;
    }
}


class Romb extends Czworakat
{
    public Romb(double a, double kat)
    {
        this.a = a;
        this.kat = kat;
        nazwa = "Romb";
    }

    public double obliczPole()
    {
        return a * a * Math.sin(Math.toRadians(kat));
    }

    public double obliczObwod()
    {
        return a * 4;
    }

    public String podajNazwe()
    {
        return nazwa;
    }
}
