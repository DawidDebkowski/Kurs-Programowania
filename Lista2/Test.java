public class Test {
    public static void main(String[] args) {
        int n = 0;
        try {
            if(args.length < 1)
                throw new BladZakresu("Brak Danych");
            
            n = Integer.parseInt(args[0]);
            
            if(n < 0)
                throw new BladZakresu("Zakres nie moze byc ujemny");
        }
        catch(NumberFormatException e) {
            System.out.println("To nie jest liczba");
            return;
        }
        catch(BladZakresu e) {
            System.out.println(e);
            return;
        }
        
        WierszTrojkataPascala wsp = new WierszTrojkataPascala(n);
        wsp.obliczWiersz();

        for(int i=1;i<args.length;i++) {
            System.out.print(args[i] + " - ");
            try {
                System.out.print(wsp.wezElement(Integer.parseInt(args[i])));
            }
            catch(NumberFormatException e) {
                System.out.print("To nie jest liczba");
            }
            catch(BladZakresu e) {
                System.out.print("Musi być w zakresie (0 do n)");
            }
            System.out.println();
        }
    }
}
