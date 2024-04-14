public class Test {
    public static void Wypisz(String nazwa, double pole, double obwod)
    {
        System.out.println(nazwa);
        System.out.print("Pole: " + pole + "\n");
        System.out.print("Obwod: " + obwod);
    }

    public static void main(String[] args)
    {   
        //Parse arguments only once
        double[] parsedArgs = new double[args.length];
        for (int i = 1; i < args.length; i++)
        {
            try
            {
                parsedArgs[i] = Double.parseDouble(args[i]);
            } catch (NumberFormatException e)
            {
                System.out.println(args[i] + "-- nie jest liczba");
                return;
            }
            if (parsedArgs[i] <= 0)
            {
                System.out.println(args[i] + "-- nie moze byc ujemny");
                return;
            }
        }

        try
        {
            Figury.EnumJedenParametr jedenParametr = null;
            Figury.EnumDwaParametry dwaParametry = null;
            Double arg2 = null;

            switch (args[0])
            {
                case "o":
                    jedenParametr = Figury.EnumJedenParametr.kolo;
                    break;
                case "c":
                    double a, b, kat;
                    a = parsedArgs[1];
                    b = parsedArgs[2];
                    kat = parsedArgs[5];
                    // Find (if it exists) the second (different) side
                    for (int i = 2; i < 5; i++)
                    {
                        if (Double.compare(parsedArgs[i], a) != 0)
                        {
                            b = parsedArgs[i];
                            break;
                        }
                    }
                    // Check for irregular shapes
                    int aCount = 0;
                    int bCount = 0;
                    for (int i = 1; i < 5; i++)
                    {
                        if (Double.compare(parsedArgs[i], a) == 0)
                            aCount++;

                        if (Double.compare(parsedArgs[i], b) == 0)
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
                        {
                            dwaParametry = Figury.EnumDwaParametry.romb;
                            arg2 = kat;
                        }
                    } else if (Double.compare(a, b) == 0)
                    {
                        jedenParametr = Figury.EnumJedenParametr.kwadrat;
                    } else
                    {
                        dwaParametry = Figury.EnumDwaParametry.prostokat;
                        arg2 = b;
                    }
                    break;

                case "p":
                    jedenParametr = Figury.EnumJedenParametr.pieciokat;
                    break;
                case "s":
                    jedenParametr = Figury.EnumJedenParametr.szesciokat;
                    break;
                default:
                    throw new WrongShape();
            }
            if(jedenParametr != null)
            {
                Wypisz(jedenParametr.podajNazwe(), jedenParametr.obliczPole(parsedArgs[1]), jedenParametr.obliczObwod(parsedArgs[1]));
            }
            else if(dwaParametry != null)
            {
                Wypisz(dwaParametry.podajNazwe(), dwaParametry.obliczPole(parsedArgs[1], arg2), dwaParametry.obliczObwod(parsedArgs[1], arg2));
            }
            else 
                throw new WrongShape();
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
}


// public static double myStrToDouble(String a) throws NegativeNumberException, NumberFormatException
// {
//     double r = Double.parseDouble(a);
//     if (r <= 0)
//         throw new NegativeNumberException();
//     return r;
// }