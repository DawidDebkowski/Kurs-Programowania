public class Figury
{
    public static void main(String[] args)
    {
        double[] arguments = new double[args.length];
        for (int i = 1; i < args.length; i++)
        {
            try
            {
                arguments[i] = Double.parseDouble(args[i]);
            } catch (NumberFormatException e)
            {
                System.out.println(args[i] + "-- nie jest liczba");
                return;
            }
            if (arguments[i] <= 0)
            {
                System.out.println(args[i] + "-- nie moze byc ujemny");
                return;
            }
        }
        Figura figura = null;
        try
        {
            switch (args[0])
            {
                case "o":

                    figura = new Kolo(arguments[1]);
                    break;
                case "c":
                    Czworakat czworakat;
                    double a, b, kat;
                    a = arguments[1];
                    b = arguments[2];
                    kat = arguments[5];
                    // Find (if it exists) the second (different) side
                    for (int i = 2; i < 5; i++)
                    {
                        if (Double.compare(arguments[i], a) != 0)
                        {
                            b = arguments[i];
                            break;
                        }
                    }
                    // Check for irregular shapes
                    int aCount = 0;
                    int bCount = 0;
                    for (int i = 1; i < 5; i++)
                    {
                        if (Double.compare(arguments[i], a) == 0)
                            aCount++;

                        if (Double.compare(arguments[i], b) == 0)
                            bCount++;
                    }

                    if ((aCount != 2 || bCount != 2) && aCount != 4)
                    {
                        throw new WrongShape();
                    }

                    // Determine the exact shape
                    if (Double.compare(90.0, kat) != 0)
                    {
                        if (aCount == 4)
                            czworakat = new Romb(a, kat);
                        else
                            throw new WrongShape();
                    } else if (Double.compare(a, b) == 0)
                    {
                        czworakat = new Kwadrat(a);
                    } else
                    {
                        czworakat = new Prostokat(a, b);
                    }
                    figura = czworakat;
                    break;

                case "p":

                    figura = new Pieciakat(arguments[1]);
                    break;
                case "s":

                    figura = new Szesciakat(arguments[1]);
                    break;
                default:
                    throw new WrongShape();
            }
            Wypisz(figura.podajNazwe(), figura.obliczPole(), figura.obliczObwod());
        } catch (IndexOutOfBoundsException e)
        {
            System.err.println("Błedna liczba argumentów");
            return;
        } catch (WrongShape e)
        {
            System.err.println("Nieprawidłowy kształt");
            return;
        }
    }

    public static void Wypisz(String nazwa, double pole, double obwod)
    {
        System.out.println(nazwa);
        System.out.print("Pole: " + pole + "\n");
        System.out.print("Obwod: " + obwod);
    }
}


class NotEnoughArguments extends Exception
{
}


class WrongShape extends Exception
{
}
