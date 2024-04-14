interface JedenParametr
{
    double obliczPole(double a);

    double obliczObwod(double a);

    String podajNazwe();
}


interface DwaParametry
{
    double obliczPole(double a, double b);

    double obliczObwod(double a, double b);

    String podajNazwe();
}


public class Figury
{
    public enum EnumJedenParametr implements JedenParametr
    {
        kolo("kolo")
        {
            public double obliczObwod(double promien)
            {
                return 2 * Math.PI * promien;
            }

            public double obliczPole(double promien)
            {
                return Math.PI * promien * promien;
            }
        },
        kwadrat("Kwadrat")
        {
            public double obliczPole(double a)
            {
                return a * a;
            }

            public double obliczObwod(double a)
            {
                return a * 4;
            }
        },
        pieciokat("pieciokat")
        {
            public double obliczObwod(double a)
            {
                return a * 5;
            }

            public double obliczPole(double a)
            {
                return a * a / 4 * Math.sqrt(25 + 10 * Math.sqrt(5));
            }
        },
        szesciokat("Szesciokat")
        {
            public double obliczObwod(double a)
            {
                return a * 6;
            }

            public double obliczPole(double a)
            {
                return 3 * a * a * Math.sqrt(3) / 2;
            }
        };

        private String nazwa;

        private EnumJedenParametr(String nazwa)
        {
            this.nazwa = nazwa;
        }

        public String podajNazwe()
        {
            return nazwa;
        }
    }
    public enum EnumDwaParametry implements DwaParametry
    {
        romb("romb")
        {
            public double obliczPole(double a, double kat)
            {
                return a * a * Math.sin(Math.toRadians(kat));
            }

            public double obliczObwod(double a, double kat)
            {
                return a * 4;
            }
        },
        prostokat("prostokat")
        {
            public double obliczPole(double a, double b)
            {
                return a * b;
            }

            public double obliczObwod(double a, double b)
            {
                return a * 2 + b * 2;
            }
        };

        private String nazwa;

        private EnumDwaParametry(String nazwa)
        {
            this.nazwa = nazwa;
        }

        public String podajNazwe()
        {
            return nazwa;
        }
    }
}


class NotEnoughArguments extends Exception
{
}


class NegativeNumberException extends Exception
{
}


class WrongShape extends Exception
{
}
